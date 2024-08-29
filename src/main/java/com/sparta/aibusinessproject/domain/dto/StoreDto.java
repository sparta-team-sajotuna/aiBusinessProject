package com.sparta.aibusinessproject.domain.dto;


import com.sparta.aibusinessproject.domain.Store;

import lombok.Builder;

@Builder
public record StoreDto(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        String operationHours,
        String closedDays,
        String deliveryAddress


) {


    // dto -> entity
    public static Store toEntity(StoreDto dto) {
        return Store.builder()
                .name(dto.name)
                .address(dto.address)
                .phone(dto.phone)
                .content(dto.content)
                .minDeliveryPrice(dto.minDeliveryPrice)
                .operationHours(dto.operationHours)
                .closedDays(dto.closedDays)
                .deliveryAddress(dto.deliveryAddress)
                .storeCategories(null)
                .build();
    }

    // entity -> dto
    // category 생성 후
    public static StoreDto from(Store store) {
        return StoreDto.builder()
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .content(store.getContent())
                .minDeliveryPrice(store.getMinDeliveryPrice())
                .operationHours(store.getOperationHours())
                .closedDays(store.getClosedDays())
                .deliveryAddress(store.getDeliveryAddress())
                .build();
    }

//    // category 생성 전
//    public static StoreDto from(Store store) {
//        return StoreDto.builder()
//                .name(store.getName())
//                .address(store.getAddress())
//                .phone(store.getPhone())
//                .content(store.getContent())
//                .minDeliveryPrice(store.getMinDeliveryPrice())
//                .operationHours(store.getOperationHours())
//                .closedDays(store.getClosedDays())
//                .deliveryAddress(store.getDeliveryAddress())
////                .user(dto.user)
//                .build();
//    }
}
