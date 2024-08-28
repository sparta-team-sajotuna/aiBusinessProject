package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import com.sparta.aibusinessproject.domain.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateRequest {
    private String name;

    private int price;

    public static Menu toEntity(MenuCreateRequest requestDto, Store store) {
        return Menu.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .store(store)
                .build();
    }
}
