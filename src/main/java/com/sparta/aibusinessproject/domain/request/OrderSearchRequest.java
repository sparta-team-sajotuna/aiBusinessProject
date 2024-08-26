package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.OrderStatus;
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
    private OrderStatus status;
    private List<Long> orderItemIds;
    private String sortBy;
    private Pageable pageable;
}
