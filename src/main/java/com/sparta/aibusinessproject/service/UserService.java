package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.request.UserModifyRequest;
import com.sparta.aibusinessproject.domain.response.UserInfoResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "User Service")
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 정보 조회
    public UserInfoResponse getUserInfo(User user) {
        return UserInfoResponse.from(user);
    }

    // 회원 정보 업데이트
    @Transactional
    public UserInfoResponse modifyUser(User user, UserModifyRequest userModifyRequest) {
        // 비밀번호는 암호화해서 저장
        String modifiedPassword = userModifyRequest.getPassword() != null ? passwordEncoder.encode(userModifyRequest.getPassword()) : null;
        user.update(userModifyRequest, modifiedPassword);
        userRepository.save(user);
        return UserInfoResponse.from(user);
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user.getUserId(), user.getUserId());
    }

}
