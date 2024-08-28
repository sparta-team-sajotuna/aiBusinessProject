package com.sparta.aibusinessproject.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.LoginRequest;
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
        try {
            LoginRequest loginRequestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
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
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String userId = userDetails.getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();

        String accessToken = jwtUtil.createAccessToken(userId, role);
//        String refreshToken = jwtUtil.createRefreshToken(userId);

        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, accessToken);
//        response.addCookie(jwtUtil.addRefreshTokenToCookie(refreshToken));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("유효하지 않은 ID or 비밀번호: {}", failed.getMessage());
        response.setStatus(401);
    }
}
