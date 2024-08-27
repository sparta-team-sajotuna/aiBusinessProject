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

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFindResponse {
    //TODO
    //private Store store;

    private List<OrderMenu> menuList = new ArrayList<>();
    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private OrderStatusEnum status;

    public static OrderFindResponse fromEntity(Order order) {
        return OrderFindResponse.builder()
                .menuList(order.getOrderMenuList())
                .paymentMethod(order.getPaymentMethod())
                .totalPrice(order.getTotalPrice())
                .requests(order.getRequests())
                .status(order.getStatus())
                .build();
    }
}
