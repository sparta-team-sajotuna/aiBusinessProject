package com.sparta.aibusinessproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email이 중복됩니다."),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "userName 이 중복됩니다."),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "패스워드가 잘못되었습니다."),

    INVALID_ORDER(HttpStatus.NOT_FOUND, "유효하지 않은 주문입니다."),
    INVALID_MENU(HttpStatus.NOT_FOUND, "유효하지 않은 메뉴입니다."),
    ORDER_CANCELLATION_NOT_ALLOWED_TIME(HttpStatus.NOT_FOUND, "5분 이내에 취소가 가능합니다.");

    private HttpStatus status;
    private String message;

}