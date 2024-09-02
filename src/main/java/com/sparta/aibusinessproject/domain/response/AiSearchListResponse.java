package com.sparta.aibusinessproject.domain.response;

public record AiSearchListResponse(
        String userName,
        String question,
        String message
) {
}
