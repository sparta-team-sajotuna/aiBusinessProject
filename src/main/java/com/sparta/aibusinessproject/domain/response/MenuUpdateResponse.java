package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateResponse {
    private UUID menuId;
    private String name;
    private int price;

    public static MenuUpdateResponse fromEntity(Menu menu) {
        return MenuUpdateResponse.builder()
                .menuId(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }
}
