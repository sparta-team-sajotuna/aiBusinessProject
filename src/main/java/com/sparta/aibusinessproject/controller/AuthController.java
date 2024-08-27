package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.SignupRequestDto;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.exception.NotValidException;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Response<String> signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw handleBindingResultErrors(bindingResult);
        }

        authService.signup(signupRequestDto);
        return Response.success("회원가입이 완료되었습니다. 로그인 후 다양한 서비스를 이용해 보세요.");
    }

    @PostMapping("/login")
    public void login() {

    }

    @PostMapping("/logout")
    public void logout() {

    }

    private NotValidException handleBindingResultErrors(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            switch (fieldError.getField()) {
                case "userId":
                    return new NotValidException(ErrorCode.INVALID_USERID);
                case "password":
                    return new NotValidException(ErrorCode.INVALID_PASSWORD);
                case "email":
                    return new NotValidException(ErrorCode.INVALID_EMAIL);
                case "phone":
                    return new NotValidException(ErrorCode.INVALID_PHONE);
            }
        }
        return new NotValidException("다시 시도해 주세요.");
    }
}
