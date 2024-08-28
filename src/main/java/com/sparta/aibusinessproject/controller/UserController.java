package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.response.UserInfoResponse;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "User Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 발급한 토큰을 사용하여 사용자 정보 조회
    @GetMapping
    public UserInfoResponse getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getUserInfo(userDetails.getUsername());
    }
}
