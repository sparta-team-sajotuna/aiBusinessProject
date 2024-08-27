package com.sparta.aibusinessproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }

//    @ExceptionHandler(NotValidException.class)
//    public ResponseEntity<?> notValidExceptionHandler(NotValidException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(Response.error(e.getMessage()));
//    }
}