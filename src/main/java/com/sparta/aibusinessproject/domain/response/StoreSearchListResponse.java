package com.sparta.aibusinessproject.domain.response;

import lombok.Builder;

@Builder
public record StoreSearchListResponse(
        String name,
        int minDeliveryPrice
        // TODO : Menu Entity 연동시 List형식으로 반환
//        List<MenuResponseDto> menuDto
){
}
