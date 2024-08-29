package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.User;
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
    private UUID storeId;
    private List<OrderMenuRequest> menuIds;

    private String requests;

    public static Order toEntity(OrderCreateRequest requestDto, Store store, User user){
        return Order.builder()
                .store(store)
                .storeName(store.getName())
                .orderMenuList(new ArrayList<>())
                .requests(requestDto.getRequests())
                .status(OrderStatusEnum.CREATED)
                .user(user)
                .build();
    }
}
