package com.sparta.aibusinessproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email이 중복됩니다."),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "userName 이 중복됩니다."),

    DUPLICATED_USERID(HttpStatus.CONFLICT, "USER ID가 중복됩니다."),

    INVALID_ORDER(HttpStatus.NOT_FOUND, "유효하지 않은 주문입니다."),
    INVALID_MENU(HttpStatus.NOT_FOUND, "유효하지 않은 메뉴입니다."),
    ORDER_CANCELLATION_NOT_ALLOWED_TIME(HttpStatus.NOT_FOUND, "5분 이내에 취소가 가능합니다."),
    INVALID_USERID(HttpStatus.BAD_REQUEST, "아이디를 형식에 맞게 입력해 주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호를 형식에 맞게 입력해 주세요."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일을 형식에 맞게 입력해 주세요."),
    INVALID_PHONE(HttpStatus.BAD_REQUEST, "휴대폰 번호를 형식에 맞게 입력해 주세요."),

    INVALID_TOKEN(HttpStatus.NOT_FOUND, "유효하지 않은 토큰입니다.");

    private HttpStatus status;
    private String message;

}