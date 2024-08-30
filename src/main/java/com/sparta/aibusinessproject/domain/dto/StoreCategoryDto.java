package com.sparta.aibusinessproject.domain.dto;

import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.StoreCategory;
import lombok.Builder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record StoreCategoryDto(
        String name,
        String address,
        String phone,
        String content,
        int minDeliveryPrice,
        LocalTime openTime,
        LocalTime closeTime,
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
                .openTiME(dto.openTime)
                .closeTiME(dto.closeTime)
                .closedDays(dto.closedDays)
                .deliveryAddress(dto.deliveryAddress)
                .storeCategories(dto.categories)
//                .user(dto.user)
                .build();
    }

    // category 생성 후
    public static StoreCategoryDto from(Store store) {
        return StoreCategoryDto.builder()
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .content(store.getContent())
                .minDeliveryPrice(store.getMinDeliveryPrice())
                .openTime(store.getOpenTiME())
                .closeTime(store.getCloseTiME())
                .closedDays(store.getClosedDays())
                .deliveryAddress(store.getDeliveryAddress())
                .categories(store.getStoreCategories())
//                .user(dto.user)
                .build();
    }
}
