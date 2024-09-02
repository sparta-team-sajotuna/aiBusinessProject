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
    public static Store toEntity(StoreDto dto,User user) {
        return Store.builder()
                .name(dto.name)
                .address(dto.address)
                .phone(dto.phone)
                .content(dto.content)
                .minDeliveryPrice(dto.minDeliveryPrice)
                .openTiME(dto.openTime)
                .closeTiME(dto.closeTime)
                .closedDays(dto.closedDays)
                .deliveryAddress(dto.deliveryAddress)
                .storeCategories(null)
                .user(user)
                .build();
    }

    // entity -> dto
    // category 생성 후
//    public static StoreDto from(Store store) {
//        return StoreDto.builder()
//                .name(store.getName())
//                .address(store.getAddress())
//                .phone(store.getPhone())
//                .content(store.getContent())
//                .minDeliveryPrice(store.getMinDeliveryPrice())
//                .openTime(store.getOpenTiME())
//                .closeTime(store.getCloseTiME())
//                .closedDays(store.getClosedDays())
//                .deliveryAddress(store.getDeliveryAddress())
//                .build();
//    }

}
