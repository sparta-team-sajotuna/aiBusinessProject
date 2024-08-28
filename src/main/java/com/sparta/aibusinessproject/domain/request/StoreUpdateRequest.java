package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.dto.StoreDto;

public record StoreUpdateRequest(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        String operationHousrs,
        String closedDays,
        String deliveryAddress
) {

    // request -> dto
    public static StoreDto toDto(StoreUpdateRequest request) {
        return StoreDto.builder()
                .name(request.name())
                .address(request.address())
                .phone(request.phone())
                .content(request.content())
                .minDeliveryPrice(request.minDeliveryPrice())
                .operationHours(request.operationHousrs())
                .closedDays(request.closedDays())
                .deliveryAddress(request.deliveryAddress())
                .build();
    }
}
