package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {
    //private List<UUID> menuIds;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private String status;

    public static Order toEntity(OrderCreateRequest requestDto){
        return Order.builder()
                .paymentMethod(requestDto.getPaymentMethod())
                .totalPrice(requestDto.getTotalPrice())
                .requests(requestDto.getRequests())
                .status(requestDto.getStatus())
                .build();
    }
}
