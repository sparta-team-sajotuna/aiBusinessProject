package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCreateResponse {
    private UUID menuId;
    private String name;
    private int price;

    public static MenuCreateResponse fromEntity(Menu menu) {
        return MenuCreateResponse.builder()
                .menuId(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }
}
