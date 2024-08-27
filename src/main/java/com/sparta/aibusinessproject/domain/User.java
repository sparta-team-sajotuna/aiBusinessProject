package com.sparta.aibusinessproject.domain;

import com.sparta.aibusinessproject.domain.request.SignupRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_users")
public class User {

    @Id
    @Column(name="user_id" ,nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address = new ArrayList<>();

    // SignupRequestDto를 User 객체로 변환하는 메서드
    public static User fromSignupRequestDto(SignupRequestDto signupRequestDto, UserRoleEnum role) {
        return User.builder()
                .userId(signupRequestDto.getUserId())
                .password(signupRequestDto.getPassword()) // 비밀번호는 암호화 후 저장할 것
                .name(signupRequestDto.getName())
                .phone(signupRequestDto.getPhone())
                .email(signupRequestDto.getEmail())
                .role(role) // 기본 역할 설정 예시
                .build();
    }
}
