package com.sparta.aibusinessproject.ai;

import com.sparta.aibusinessproject.domain.Ai;
import com.sparta.aibusinessproject.domain.User;

public record AiDto(
        User user,
        String text,
        String aiResult
) {

    public static Ai AiDto(User user,String text,String aiResult){
        return Ai.builder()
                .user(user)
                .question(text)
                .message(aiResult)
                .build();
    }
}
