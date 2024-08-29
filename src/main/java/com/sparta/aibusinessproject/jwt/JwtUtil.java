package com.sparta.aibusinessproject.jwt;

import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.exception.NotValidException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/*
* JWT 발급과 검증을 담당하는 클래스
* 가장 최신 방식인 0.12.3방식 사용.
* 주의 11. 버전과 많은 차이가 있음!
*
 */
@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {
    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // 사용자 권한 값의 KEY
    public static final String AUTHORIZATION_KEY = "auth";
    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";
    // Access Token 만료기간
    private final long ACCESS_TOKEN_TIME = 7*24 * 60 * 60 * 1000L; // 60분 * 7
    // Refresh Token 만료기간
    private final long REFRESH_TOKEN_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일


    private SecretKey secretKey;
    public JwtUtil(@Value("${jwt.secret.key}") String secret) {
        // 가져온 키를 암호화해서 SetcretKey객체 타입으로 저장
        // 양방향 암호화의 대칭키 방식 사용: 동일한 방식으로 암호화, 복호화 진행.. cf. 비대칭키 방식으로도 가능
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // Access Token 생성
    public String createAccessToken(String userId, UserRoleEnum role) {

        return BEARER_PREFIX +
                Jwts.builder()
                        .claim("userId", userId) // 사용자 식별자값(ID)
                        .claim(AUTHORIZATION_KEY, role) // 사용자 권한
                        .issuedAt(new Date(System.currentTimeMillis())) // 토큰 발급일
                        .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME)) // 토큰 만료일
                        .signWith(secretKey) // 토큰 암호화
                        .compact();
    }

    // Refresh Token 생성
    // 엑세스 토큰의 갱신, 장기 세션 유지 등의 역할을 하므로, role의 정보는 넣지 않음.
    public String createRefreshToken(String userId) {
        return Jwts.builder()
                .claim("userId", userId) // 사용자 식별자값(ID)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME)) // 토큰 만료일
                .signWith(secretKey) // 토큰 암호화
                .compact();
    }

    // Refresh Token 생성 후 Cookie에 담기
    public Cookie addRefreshTokenToCookie(String refreshToken) {
        // Cookie Value 에는 공백이 불가능하므로 encoding 진행
        try {
            refreshToken = URLEncoder.encode(refreshToken, "utf-8").replaceAll("\\+", "%20");
            Cookie refreshTokenCookie = new Cookie("RefreshTokenCookie", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge((int) (REFRESH_TOKEN_TIME / 1000));
            return refreshTokenCookie;
        } catch (UnsupportedEncodingException e) {
            log.error("Refresh Token encoding 오류: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Header에서 Access Token 가져오기
    public String getAccessTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7).trim(); // 순수한 토큰 가져오기 위해 substring(BEARER 자름)
        }
        return null;
    }
    // Cookie에서 Refresh Token 가져오기


    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다. 사용자 ID: {}", getUserIdFromAccessToken(token));
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다. 사용자 ID: {}", getUserIdFromAccessToken(token));
        } catch (MalformedJwtException | SecurityException e) {
            log.error("Invalid JWT token, 유효하지 않은 JWT token 입니다. 사용자 ID: {}", getUserIdFromAccessToken(token));
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다. 사용자 ID: {}", getUserIdFromAccessToken(token));
        }
        return false;
    }

    // Access토큰에서 사용자 정보 가져오기
    public String getUserIdFromAccessToken(String accessToken) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(accessToken).getPayload().get("userId", String.class);
    }
    public UserRoleEnum getUserRoleFromAccessToken(String accessToken) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(accessToken).getPayload().get(AUTHORIZATION_KEY, UserRoleEnum.class);
    }

    public Boolean isExpiredToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }
}
