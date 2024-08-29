package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoResponse {
    private UUID menuId;
    private String name;
    private int quantity;

    public static MenuInfoResponse fromOrderMenu(OrderMenu orderMenu){
        return MenuInfoResponse.builder()
                .menuId(orderMenu.getMenu().getId())
                .name(orderMenu.getMenu().getName())
                .quantity(orderMenu.getQuantity())
                .build();
    }
}
