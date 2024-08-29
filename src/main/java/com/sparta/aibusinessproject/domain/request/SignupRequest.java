package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디를 형식에 맞게 입력해 주세요.")
    private String userId;
    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,15}$",
            message = "비밀번호를 형식에 맞게 입력해 주세요."
    )
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "휴대폰 번호를 형식에 맞게 입력해 주세요.")
    private String phone;
    @NotBlank
    @Email(message = "이메일을 형식에 맞게 입력해 주세요.")
    private String email;

    @Builder.Default
    private boolean owner = false;
    @Builder.Default
    private String ownerToken = "";

    private String currentAddress;

    public void setPassword(String password) {
        this.password = password;
    }

    // SignupRequest를 User 객체로 변환하는 메서드
    public static User toEntity(SignupRequest signupRequest, UserRoleEnum role) {
        return User.builder()
                .userId(signupRequest.getUserId())
                .password(signupRequest.getPassword()) // 비밀번호는 암호화 후 저장할 것
                .name(signupRequest.getName())
                .phone(signupRequest.getPhone())
                .email(signupRequest.getEmail())
                .role(role) // 기본 역할 설정 예시
                .build();
    }
}
