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

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${owner.token}")
    private String ownerToken;

    public void signup(SignupRequest signupRequestDto) {
        // ID 중복 확인
        if (userRepository.existsById(signupRequestDto.getUserId())) {
            throw new NotValidException(ErrorCode.DUPLICATED_USERID);
        }
        // PASSWORD 암호화
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        signupRequestDto.setPassword(password);
        // PHONE 중복 확인
        if (userRepository.existsByPhone(signupRequestDto.getPhone())) {
            throw new NotValidException(ErrorCode.DUPLICATED_PHONE);
        }
        // EMAIL 중복 확인
        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new NotValidException(ErrorCode.DUPLICATED_EMAIL);
        }
        // 사용자 ROLE 확인 // owner 값이 true이면 token이 있음.
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (signupRequestDto.isOwner()) {
            if (!ownerToken.equals(signupRequestDto.getOwnerToken())) {
                throw new NotValidException(ErrorCode.INVALID_TOKEN);
            }
            role = UserRoleEnum.OWNER;
        }

        userRepository.save(User.fromSignupRequestDto(signupRequestDto, role));
    }



}
