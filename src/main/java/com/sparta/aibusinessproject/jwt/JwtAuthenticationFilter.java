package com.sparta.aibusinessproject.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.LoginRequestDto;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 로그인 요청만 처리하도록 설정
        if (!"POST".equalsIgnoreCase(request.getMethod()) || !request.getRequestURI().equals("/api/v1/auth/login")) {
            return null;
        }
        try {
            LoginRequestDto loginRequestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            log.info(loginRequestDto.getUserId());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUserId(),
                            loginRequestDto.getPassword(),
                            null // 권한 정보인데, 인증 요청을 처리하는 데에는 필요하지 않음. 권한 정보는 인증 요청이 성공하면 Authentication객체를 통해 반환
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userId = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        log.info(userId);
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
        log.info(role.toString());
        String accessToken = jwtUtil.createAccessToken(userId, role); // Access Token 생성
        String refreshToken = jwtUtil.createRefreshToken(userId); // Refresh Token 생성
        // accessToken 헤더에 추가
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        // refreshToken 쿠키에 추가
        response.addCookie(jwtUtil.addRefreshTokenToCookie(refreshToken));

    }

    // 인증 실패 시 BadCredentialsException예외 발생
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.error("유효하지 않은 ID or 비밀번호: {}", failed.getMessage());
    }
}
