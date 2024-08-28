package com.sparta.aibusinessproject.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {
    String userId;
    String name;
    String phone;
    String email;
    String role;
}
