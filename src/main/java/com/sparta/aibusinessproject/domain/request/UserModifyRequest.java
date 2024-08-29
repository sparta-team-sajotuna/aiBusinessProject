package com.sparta.aibusinessproject.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyRequest {

    @Size(min = 8, max = 15)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,15}$",
            message = "비밀번호를 형식에 맞게 수정해 주세요."
    )
    private String password;

    private String name;

    @Size(min = 13, max = 13)
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "휴대폰 번호를 형식에 맞게 입력해 주세요.")
    private String phone;

    @Email(message = "이메일을 형식에 맞게 입력해 주세요.")
    private String email;

}
