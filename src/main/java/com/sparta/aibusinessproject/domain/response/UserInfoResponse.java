package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    String userId;
    String name;
    String phone;
    String email;
    String role;

    public static UserInfoResponse toUserInfoResponse(User user) {
        return UserInfoResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole().getAuthority())
                .build();
    }
}
