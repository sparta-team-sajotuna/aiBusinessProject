package com.sparta.aibusinessproject.domain.dto;

import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.StoreCategory;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record StoreCategoryDto(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        String operationHours,
        String closedDays,
        String deliveryAddress,
        List<StoreCategory> categories
) {

        public StoreCategoryDto {
        // categories가 null일 경우 빈 ArrayList로 초기화
        if (categories == null) {
            categories = new ArrayList<>();
        }
    }

    // dto -> entity
    public static Store toEntity(StoreCategoryDto dto) {
        return Store.builder()
                .name(dto.name)
                .address(dto.address)
                .phone(dto.phone)
                .content(dto.content)
                .minDeliveryPrice(dto.minDeliveryPrice)
                .operationHours(dto.operationHours)
                .closedDays(dto.closedDays)
                .deliveryAddress(dto.deliveryAddress)
                .storeCategories(dto.categories)
//                .user(dto.user)
                .build();
    }

    // category 생성 후
    public static StoreCategoryDto from(Store store, List<StoreCategory> categories) {
        return StoreCategoryDto.builder()
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .content(store.getContent())
                .minDeliveryPrice(store.getMinDeliveryPrice())
                .operationHours(store.getOperationHours())
                .closedDays(store.getClosedDays())
                .deliveryAddress(store.getDeliveryAddress())
                .categories(categories)
//                .user(dto.user)
                .build();
    }
}
