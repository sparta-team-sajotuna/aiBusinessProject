package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {
    private List<OrderMenuRequest> menuIds;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private OrderStatus status;

    public static Order toEntity(OrderCreateRequest requestDto){
        return Order.builder()
                .orderMenuList(new ArrayList<>())
                .paymentMethod(requestDto.getPaymentMethod())
                .totalPrice(requestDto.getTotalPrice())
                .requests(requestDto.getRequests())
                .status(requestDto.getStatus())
                .build();
    }
}
