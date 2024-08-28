package com.sparta.aibusinessproject.domain.request;


public record StoreSearchListRequest(
        String category,
        String deliveryAddress,
        int size
) {
}
