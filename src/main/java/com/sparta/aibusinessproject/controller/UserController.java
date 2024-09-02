package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.UserModifyRequest;
import com.sparta.aibusinessproject.domain.response.UserInfoResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User API", description = "유저 CRUD - 마이페이지")
public class UserController {

    private final UserService userService;

    // 발급한 토큰을 사용하여 사용자 정보 조회
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping
    @Operation(summary = "유저 조회", description = "특정 유저에 대한 정보 조회")
    public Response<UserInfoResponse> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(userService.getUserInfo(userDetails.getUser()));
    }

    // 회원 정보 업데이트
    // 본인이 본인 정보 수정
    @PatchMapping
    @Operation(summary = "유저 수정", description = "특정 유저에 대한 정보 수정")
    public Response<UserInfoResponse> modifyUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid UserModifyRequest userModifyRequest) {
        return Response.success(userService.modifyUser(userDetails.getUser(), userModifyRequest));
    }

    // 회원 탈퇴
    // 본인이 본인 탈퇴
    @DeleteMapping
    @Operation(summary = "유저 삭제", description = "특정 유저 탈퇴")
    public Response<String> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.deleteUser(userDetails.getUser());
        return Response.success("회원 탈퇴가 완료되었습니다.");
    }
}
