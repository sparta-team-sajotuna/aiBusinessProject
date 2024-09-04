package com.sparta.aibusinessproject.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuModifyRequest {
    private String name;
    private Integer quantity;
    private Integer price;
}
