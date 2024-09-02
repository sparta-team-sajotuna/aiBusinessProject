package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Store;
import lombok.Builder;

import java.time.LocalTime;
import java.util.UUID;

@Builder
public record StoreCreateResponse(
        UUID id,
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

    public static StoreCreateResponse fromEntity(Store store) {
        return StoreCreateResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .content(store.getContent() != null ? store.getContent() : null)
                .minDeliveryPrice(store.getMinDeliveryPrice())
                .openTime(store.getOpenTiME())
                .closeTime(store.getCloseTiME())
                .closedDays(store.getClosedDays())
                .deliveryAddress(store.getDeliveryAddress())
                .build();
    }
}
