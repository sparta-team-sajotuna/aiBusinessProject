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

    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "userName 이 중복됩니다."),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "패스워드가 잘못되었습니다."),

    DUPLICATED_STORENAME(HttpStatus.CONFLICT, "가게 이름이 중복됩니다."),
    INVALID_STORE(HttpStatus.NOT_FOUND,"가게 정보가 없습니다."),
    INVALID_CATEGORY(HttpStatus.NOT_FOUND,"카테고리 정보가 없습니다. 다른 카테고리를 입력해 주세요"),
    DUPLICATED_CATEGORY(HttpStatus.CONFLICT, "카테고리 이름이 중복됩니다."),
    NOTFOUND_CATEGORY(HttpStatus.NOT_FOUND,"카테고리가 존재하지 않습니다."),
    DEFAULT_VALUE(HttpStatus.BAD_REQUEST, "기본 카테고리 종류 이므로 변경이 불가능합니다."),
    ALREADY_CATEGORY(HttpStatus.ALREADY_REPORTED, "이미 존재하는 카테고리 입니다");

    DUPLICATED_USERID(HttpStatus.CONFLICT, "USER ID가 중복됩니다."),
    INVALID(HttpStatus.BAD_REQUEST, "다시 시도해 주세요."),

    INVALID_ORDER(HttpStatus.NOT_FOUND, "유효하지 않은 주문입니다."),
    INVALID_MENU(HttpStatus.NOT_FOUND, "유효하지 않은 메뉴입니다."),
    ORDER_CANCELLATION_NOT_ALLOWED_TIME(HttpStatus.BAD_REQUEST, "5분 이내에 취소가 가능합니다."),

    INVALID_USERID(HttpStatus.BAD_REQUEST, "아이디를 형식에 맞게 입력해 주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호를 형식에 맞게 입력해 주세요."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일을 형식에 맞게 입력해 주세요."),
    INVALID_PHONE(HttpStatus.BAD_REQUEST, "휴대폰 번호를 형식에 맞게 입력해 주세요."),

    PAYMENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 승인된 결제입니다."),
    INVALID_PAYMENT(HttpStatus.NOT_FOUND, "유효하지 않은 결제입니다."),
    NOT_YOUR_PAYMENT(HttpStatus.BAD_REQUEST, "회원님의 결제내역이 아닙니다."),
    ORDER_AND_PAYMENT_MISMATCH(HttpStatus.BAD_REQUEST, "결제의 주문 ID가 요청된 주문 ID와 일치하지 않습니다."),


    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.NOT_FOUND, "유효하지 않은 토큰입니다.");


    private HttpStatus status;
    private String message;

}