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

    private String storeName;

    private UUID orderId;
    private String paymentMethod;

    private int totalPrice;

    private String requests;

    private OrderStatusEnum status;

    @Builder.Default
    private List<MenuInfoResponse> menuList = new ArrayList<>();

    public static OrderFindResponse fromEntity(Order order) {
        return OrderFindResponse.builder()
                .storeName(order.getStoreName())
                .orderId(order.getId())
                .paymentMethod(order.getPaymentMethod())
                .totalPrice(order.getTotalPrice())
                .requests(order.getRequests())
                .status(order.getStatus())
                .menuList(order.getOrderMenuList().stream()
                        .map(OrderMenu::toMenuInfo).toList())
                .build();
    }
}
