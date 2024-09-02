package com.sparta.aibusinessproject.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<?> notValidExceptionHandler(NotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(Response.error(errorResponse), HttpStatus.BAD_REQUEST);
    }

    // 회원가입 시 입력한 형식이 맞지 않을 때 발생하는 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // Validation 오류를 수집
        BindingResult bindingResult = e.getBindingResult();
        // 오류 처리
        ErrorResponse errorResponse = handleBindingResultErrors(bindingResult);

        return new ResponseEntity<>(Response.error(errorResponse), HttpStatus.BAD_REQUEST);

    }

    // 유효성 검사 시 어느 부분에서 예외가 발생했는지 확인하는 메서드
    private ErrorResponse handleBindingResultErrors(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            switch (fieldError.getField()) {
                case "userId":
                    return new ErrorResponse(ErrorCode.INVALID_USERID, ErrorCode.INVALID_USERID.getMessage());
                case "password":
                    return new ErrorResponse(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
                case "phone":
                    return new ErrorResponse(ErrorCode.INVALID_PHONE, ErrorCode.INVALID_PHONE.getMessage());
                case "email":
                    return new ErrorResponse(ErrorCode.INVALID_EMAIL, ErrorCode.INVALID_EMAIL.getMessage());
            }
        }
        return new ErrorResponse(ErrorCode.INVALID,bindingResult.getFieldErrors().stream()
                .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .collect(Collectors.joining(", ")));
    }
}