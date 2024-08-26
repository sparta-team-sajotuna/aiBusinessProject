package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.SignupRequestDto;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${owner.token}")
    private String ownerToken;

    public void signup(SignupRequestDto signupRequestDto) {
        // ID 중복 확인
        String userId = signupRequestDto.getUserId();
        Optional<User> checkUserId = userRepository.findById(UUID.fromString(userId));
        if (checkUserId.isPresent()) {
            throw new ApplicationException(ErrorCode.DUPLICATED_USERID);
        }
        // PASSWORD 암호화
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // EMAIL 중복 확인
        String email = signupRequestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkUserId.isPresent()) {
            throw new ApplicationException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 사용자 ROLE 확인 // owner 값이 true이면 token이 있음.
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (signupRequestDto.isOwner()) {
            if (!ownerToken.equals(signupRequestDto.getOwnerToken())) {
                throw new ApplicationException(ErrorCode.INVALID_TOKEN);
            }
            role = UserRoleEnum.OWNER;
        }

    }
}
