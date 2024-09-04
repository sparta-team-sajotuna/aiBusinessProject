package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.dto.StoreDto;

import java.time.LocalTime;


public record StoreCreateRequest(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        LocalTime openTime,
        LocalTime closeTime,
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
                .openTime(request.openTime())
                .closeTime(request.closeTime())
                .closedDays(request.closedDays())
                .deliveryAddress(request.deliveryAddress())
                .build();
    }
}
