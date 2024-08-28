package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.StoreCategory;
import com.sparta.aibusinessproject.domain.dto.StoreDto;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record StoreSearchResponse(
        String name,
        String content,
        int minDeliveryPrice,
        String operationHours,
        String closedDays,
        String deliveryAddress
//        List<StoreCategory> categories
//      List<MenuResponseDto> menu
) {
//    public StoreSearchResponse {
//        // categories가 null일 경우 빈 ArrayList로 초기화
//        if (categories == null) {
//            categories = new ArrayList<>();
//        }
//    }


    // entity -> searchDto  새로 생성하는 경우
    public static StoreSearchResponse from(Store store) {
        return StoreSearchResponse.builder()
                .name(store.getName())
                .content(store.getContent())
                .minDeliveryPrice(store.getMinDeliveryPrice())
                .operationHours(store.getOperationHours())
                .closedDays(store.getClosedDays())
                .deliveryAddress(store.getDeliveryAddress())
                .build();
    }

}
