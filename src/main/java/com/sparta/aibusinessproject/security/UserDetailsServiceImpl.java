package com.sparta.aibusinessproject.security;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * 사용자 인증을 처리하고 UserDetails객체 반환
 * DB에서 회원정보를 조회해서 반환
 *
 */
@Slf4j(topic = "UserDetails Service")
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // user정보를 확인하고 UserDetailsImpl 생성자로 보내서 UserDetailsImpl을 반환
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ApplicationException(ErrorCode.USER_NOT_FOUND)
        );
        return new UserDetailsImpl(user);
    }
}
