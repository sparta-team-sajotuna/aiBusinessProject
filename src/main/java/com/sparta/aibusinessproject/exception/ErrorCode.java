package com.sparta.aibusinessproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자를 찾지 못하였습니다."),

    DUPLICATED_PHONE(HttpStatus.CONFLICT, "휴대폰 번호가 중복됩니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email이 중복됩니다."),
    DUPLICATED_USERID(HttpStatus.CONFLICT, "USER ID가 중복됩니다."),

    INVALID(HttpStatus.BAD_REQUEST, "다시 시도해 주세요."),
    INVALID_USERID(HttpStatus.BAD_REQUEST, "아이디를 형식에 맞게 입력해 주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호를 형식에 맞게 입력해 주세요."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일을 형식에 맞게 입력해 주세요."),
    INVALID_PHONE(HttpStatus.BAD_REQUEST, "휴대폰 번호를 형식에 맞게 입력해 주세요."),

    INVALID_TOKEN(HttpStatus.NOT_FOUND, "유효하지 않은 토큰입니다.");

    private HttpStatus status;
    private String message;

}