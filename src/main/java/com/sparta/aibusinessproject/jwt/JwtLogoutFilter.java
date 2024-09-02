package com.sparta.aibusinessproject.jwt;

import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.RefreshRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j(topic = "JWT 로그아웃")
@RequiredArgsConstructor
public class JwtLogoutFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        // 로그아웃 요청이 아니거나 POST요청이 아니면 다음 필터로..
        if (!requestURI.matches("^\\/logout$") || !requestMethod.equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }
        // refresh 토큰 가져오기
        String refreshTokenFromCookie = jwtUtil.getRefreshTokenFromCookie(request);
        // null이거나 존재하지 않는 refreshToken이면 예외
        if (refreshTokenFromCookie == null || !refreshRepository.existsByRefresh(refreshTokenFromCookie)) {
            throw new ApplicationException(ErrorCode.INVALID_TOKEN);
        }
        // 존재하고, 만료되지 않은 refreshToken이면 db에서 삭제 처리
        if (jwtUtil.isNotExpiredRefreshToken(refreshTokenFromCookie)) {
            refreshRepository.deleteByRefresh(refreshTokenFromCookie);

            Cookie cookie = new Cookie("RefreshTokenCookie", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");

            response.addCookie(cookie);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
