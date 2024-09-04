package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderFindResponse> searchOrders(OrderSearchRequest searchDto, Pageable pageable, UserRoleEnum role, String userId);
}
