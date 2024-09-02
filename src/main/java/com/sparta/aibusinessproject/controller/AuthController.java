package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.SignupRequest;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth API", description = "회원 가입 & 로그아웃")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "SighUp", description = "회원 가입(정해진 형식에 맞춰 회원가입)")
    public Response<String> signup(@RequestBody @Valid SignupRequest signupRequestDto) {
        log.info(signupRequestDto.toString());
        authService.signup(signupRequestDto);
        return Response.success("회원가입이 완료되었습니다. 로그인 후 다양한 서비스를 이용해 보세요.");
    }

}
