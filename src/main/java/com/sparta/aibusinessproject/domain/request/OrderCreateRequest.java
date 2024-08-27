package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {
    private List<OrderMenuRequest> menuIds;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private OrderStatusEnum status;

    public static Order toEntity(OrderCreateRequest requestDto){
        return Order.builder()
                .orderMenuList(new ArrayList<>())
                .paymentMethod(requestDto.getPaymentMethod())
                .totalPrice(requestDto.getTotalPrice())
                .requests(requestDto.getRequests())
                .status(OrderStatusEnum.CREATED)
                .build();
    }
}
