package com.sparta.aibusinessproject.ai;

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
