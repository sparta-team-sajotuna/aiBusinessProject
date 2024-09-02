package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.SignupRequest;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.repository.RefreshRepository;
import com.sparta.aibusinessproject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "Auth Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Response<String> signup(@RequestBody @Valid SignupRequest signupRequestDto) {
        log.info(signupRequestDto.toString());
        authService.signup(signupRequestDto);
        return Response.success("회원가입이 완료되었습니다. 로그인 후 다양한 서비스를 이용해 보세요.");
    }

    @PostMapping("/reissue")
    public Response<?> reIssue(HttpServletRequest request, HttpServletResponse response) {
        authService.reIssue(request, response);
        return Response.success("Access Token 재발급 완료");
    }
}
