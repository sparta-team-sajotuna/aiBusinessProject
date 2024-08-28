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
    ORDER_CANCELLATION_NOT_ALLOWED_TIME(HttpStatus.BAD_REQUEST, "5분 이내에 취소가 가능합니다."),
    INVALID_USERID(HttpStatus.BAD_REQUEST, "아이디를 형식에 맞게 입력해 주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호를 형식에 맞게 입력해 주세요."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일을 형식에 맞게 입력해 주세요."),
    INVALID_PHONE(HttpStatus.BAD_REQUEST, "휴대폰 번호를 형식에 맞게 입력해 주세요."),

    INVALID_TOKEN(HttpStatus.NOT_FOUND, "유효하지 않은 토큰입니다."),

    PAYMENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 승인된 결제입니다."),
    INVALID_PAYMENT(HttpStatus.NOT_FOUND, "유효하지 않은 결제입니다."),
    NOT_YOUR_PAYMENT(HttpStatus.BAD_REQUEST, "회원님의 결제내역이 아닙니다."),
    ORDER_AND_PAYMENT_MISMATCH(HttpStatus.BAD_REQUEST, "결제의 주문 ID가 요청된 주문 ID와 일치하지 않습니다.");



    private HttpStatus status;
    private String message;

}