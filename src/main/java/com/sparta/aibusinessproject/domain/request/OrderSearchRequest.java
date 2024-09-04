package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchRequest {
    private OrderStatusEnum status;
    private String sortBy;
    private Pageable pageable;
}
