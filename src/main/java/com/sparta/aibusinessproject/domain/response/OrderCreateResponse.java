package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponse {

    UUID orderId;
    String storeName;
    //String List<Menu> menuList = new ArrayList<>();
    int totalPrice;
    String status;

    public static OrderCreateResponse fromEntity(Order order) {
        return OrderCreateResponse.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
