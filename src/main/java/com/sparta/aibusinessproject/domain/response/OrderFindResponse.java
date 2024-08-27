package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderMenu;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFindResponse {
    //TODO
    //private Store store;

    private UUID orderId;
    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private OrderStatusEnum status;

    private List<OrderMenu> menuList = new ArrayList<>();

    public static OrderFindResponse fromEntity(Order order) {
        return OrderFindResponse.builder()
                .orderId(order.getId())
                .paymentMethod(order.getPaymentMethod())
                .totalPrice(order.getTotalPrice())
                .requests(order.getRequests())
                .status(order.getStatus())
                .menuList(order.getOrderMenuList())
                .build();
    }
}
