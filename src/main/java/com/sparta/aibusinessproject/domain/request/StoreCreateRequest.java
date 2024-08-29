package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.dto.StoreDto;


public record StoreCreateRequest(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        String operationHours,
        String closedDays,
        String deliveryAddress,
        String category
) {

    // request -> dto
    public static StoreDto toDto(StoreCreateRequest request) {
        return StoreDto.builder()
                .name(request.name())
                .address(request.address())
                .phone(request.phone())
                .content(request.content())
                .minDeliveryPrice(request.minDeliveryPrice())
                .operationHours(request.operationHours())
                .closedDays(request.closedDays())
                .deliveryAddress(request.deliveryAddress())
                .build();
    }
}
