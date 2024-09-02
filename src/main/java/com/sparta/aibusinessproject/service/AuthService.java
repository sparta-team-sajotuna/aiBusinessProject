package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.RefreshToken;
import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.SignupRequest;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.exception.NotValidException;
import com.sparta.aibusinessproject.jwt.JwtUtil;
import com.sparta.aibusinessproject.repository.RefreshRepository;
import com.sparta.aibusinessproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.sparta.aibusinessproject.jwt.JwtUtil.REFRESH_TOKEN_TIME;

@Slf4j(topic = "Auth Service")
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Value("${owner.token}")
    private String ownerToken;

    public void signup(SignupRequest signupRequest) {
        // ID 중복 확인
        if (userRepository.existsById(signupRequest.getUserId())) {
            throw new NotValidException(ErrorCode.DUPLICATED_USERID);
        }
        // PASSWORD 암호화
        String password = passwordEncoder.encode(signupRequest.getPassword());
        signupRequest.setPassword(password);
        // PHONE 중복 확인
        if (userRepository.existsByPhone(signupRequest.getPhone())) {
            throw new NotValidException(ErrorCode.DUPLICATED_PHONE);
        }
        // EMAIL 중복 확인
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new NotValidException(ErrorCode.DUPLICATED_EMAIL);
        }
        // 사용자 ROLE 확인 // owner 값이 true이면 token이 있음.
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (signupRequest.isOwner()) {
            if (!ownerToken.equals(signupRequest.getOwnerToken())) {
                throw new NotValidException(ErrorCode.INVALID_TOKEN);
            }
            role = UserRoleEnum.OWNER;
        }
        userRepository.save(SignupRequest.toEntity(signupRequest, role));
    }

    public void reIssue(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtUtil.getRefreshTokenFromCookie(request);
        if (refreshToken == null || !refreshRepository.existsByRefresh(refreshToken)) {
            throw new ApplicationException(ErrorCode.INVALID_TOKEN);
        }
        if (jwtUtil.isNotExpiredRefreshToken(refreshToken)) {
            String userId = jwtUtil.getUserIdFromRefreshToken(refreshToken);
            String userRole = jwtUtil.getUserRoleFromRefreshToken(refreshToken);
            String reIssuedAccessToken = jwtUtil.createAccessToken(userId, userRole);
            String newRefreshToken = jwtUtil.createRefreshToken(userId, userRole);
            saveRefreshToken(userId, newRefreshToken);

            response.addHeader(jwtUtil.AUTHORIZATION_HEADER, reIssuedAccessToken);
            response.addCookie(jwtUtil.addRefreshTokenToCookie(newRefreshToken));
        }

    }
    // Refresh Token 서버에 저장
    private void saveRefreshToken(String userId, String refresh) {
        Date date = new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME);
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .refresh(refresh)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refreshToken);
    }

}
