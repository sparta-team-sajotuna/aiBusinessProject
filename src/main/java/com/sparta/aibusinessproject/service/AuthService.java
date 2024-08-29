package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.SignupRequest;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.exception.NotValidException;
import com.sparta.aibusinessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j(topic = "Auth Service")
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${owner.token}")
    private String ownerToken;

    public void signup(SignupRequest signupRequest) {
        // ID 중복 확인
        if (userRepository.existsById(signupRequest.getUserId())) {
            throw new NotValidException(ErrorCode.DUPLICATED_USERID);
        }
        // PASSWORD 암호화
        String password = passwordEncoder.encode(signupRequest.getPassword());
        signupRequest.setPassword(password);
        // PHONE 중복 확인
        if (userRepository.existsByPhone(signupRequest.getPhone())) {
            throw new NotValidException(ErrorCode.DUPLICATED_PHONE);
        }
        // EMAIL 중복 확인
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new NotValidException(ErrorCode.DUPLICATED_EMAIL);
        }
        // 사용자 ROLE 확인 // owner 값이 true이면 token이 있음.
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (signupRequest.isOwner()) {
            if (!ownerToken.equals(signupRequest.getOwnerToken())) {
                throw new NotValidException(ErrorCode.INVALID_TOKEN);
            }
            role = UserRoleEnum.OWNER;
        }

        userRepository.save(SignupRequest.toEntity(signupRequest, role));
    }

}
