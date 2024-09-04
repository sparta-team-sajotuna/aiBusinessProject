package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Ai;
import lombok.Builder;

@Builder
public record AiSearchResponse(
        String question,
        String message
){

    public static AiSearchResponse from(Ai ai){
        return AiSearchResponse.builder()
                .question(ai.getQuestion())
                .message(ai.getMessage())
                .build();
    }
}
