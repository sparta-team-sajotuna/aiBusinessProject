package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFindResponse {

    //private Store store;
    //private List<Menu> menuList;
    private String paymentMethod;

    private int totalPrice;

    private String requests;

    //TODO: Enum으로 수정
    private OrderStatus status;//접수, 결제전, 결제 완료

    public static OrderFindResponse fromEntity(Order order) {
        return OrderFindResponse.builder()
                .paymentMethod(order.getPaymentMethod())
                .totalPrice(order.getTotalPrice())
                .requests(order.getRequests())
                .status(order.getStatus())
                .build();
    }
}
