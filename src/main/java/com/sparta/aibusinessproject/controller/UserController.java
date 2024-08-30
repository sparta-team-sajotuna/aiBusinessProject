package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.UserModifyRequest;
import com.sparta.aibusinessproject.domain.response.UserInfoResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "User Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 발급한 토큰을 사용하여 사용자 정보 조회
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping
    public Response<UserInfoResponse> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(userService.getUserInfo(userDetails.getUser()));
    }

    // 회원 정보 업데이트
    // 본인이 본인 정보 수정
    @PatchMapping
    public Response<UserInfoResponse> modifyUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid UserModifyRequest userModifyRequest) {
        return Response.success(userService.modifyUser(userDetails.getUser(), userModifyRequest));
    }

    // 회원 탈퇴
    // 본인이 본인 탈퇴
    @DeleteMapping
    public Response<String> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.deleteUser(userDetails.getUser());
        return Response.success("회원 탈퇴가 완료되었습니다.");
    }
}
