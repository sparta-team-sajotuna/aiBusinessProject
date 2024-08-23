package com.sparta.aibusinessproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email이 중복됩니다."),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "userName 이 중복됩니다."),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "패스워드가 잘못되었습니다.");

    private HttpStatus status;
    private String message;

}