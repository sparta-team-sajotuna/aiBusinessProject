package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private UUID storeId;
    @NotNull
    private List<OrderMenuRequest> menuIds;
    @NotBlank
    private String requests;
    @NotNull
    private UUID addressId;

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
