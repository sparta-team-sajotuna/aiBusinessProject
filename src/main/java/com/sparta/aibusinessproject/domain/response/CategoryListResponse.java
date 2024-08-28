package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Category;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CategoryListResponse(
    UUID categoryId,
    String name
) {

    public static CategoryListResponse from(Category category) {
         return CategoryListResponse.builder()
                 .categoryId(category.getId())
                 .name(category.getName())
                 .build();
    }
}
