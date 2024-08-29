package com.sparta.aibusinessproject.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuRequest {
    private UUID menuId;
    private int quantity;
}
