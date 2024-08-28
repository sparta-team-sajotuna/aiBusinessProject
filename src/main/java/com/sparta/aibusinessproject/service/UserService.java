package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.response.UserInfoResponse;
import com.sparta.aibusinessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j(topic = "User Service")
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(String userId) {
        userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("해당 유저는 존재하지 않습니다."));
        return null;
    }
}
