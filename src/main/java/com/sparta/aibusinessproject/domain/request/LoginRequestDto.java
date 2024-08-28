package com.sparta.aibusinessproject.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    // spring security에서는 username을 사용한다고 하는데, 일단 한 번 userId로 시도
    private String userId;
    private String password;
}
