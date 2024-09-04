package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import com.sparta.aibusinessproject.domain.Store;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateRequest {
    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Integer quantity;

    public static Menu toEntity(MenuCreateRequest requestDto, Store store) {
        return Menu.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .quantity(requestDto.getQuantity())
                .store(store)
                .build();
    }
}
