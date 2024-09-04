package com.sparta.aibusinessproject.domain.dto;


import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.User;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record StoreDto(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        LocalTime openTime,
        LocalTime closeTime,
        String closedDays,
        String deliveryAddress
) {


    // dto -> entity
    public static Store toEntity(StoreDto dto,User user,String text) {
        return Store.builder()
                .name(dto.name)
                .address(dto.address)
                .phone(dto.phone)
                .content(text)
                .minDeliveryPrice(dto.minDeliveryPrice)
                .openTiME(dto.openTime)
                .closeTiME(dto.closeTime)
                .closedDays(dto.closedDays)
                .deliveryAddress(dto.deliveryAddress)
                .storeCategories(null)
                .user(user)
                .build();
    }


}
