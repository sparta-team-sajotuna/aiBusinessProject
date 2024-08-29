package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModifyRequest {
    OrderStatusEnum status;
}
