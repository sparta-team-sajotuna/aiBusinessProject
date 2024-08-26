package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.OrderCreateResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static com.sparta.aibusinessproject.exception.ErrorCode.INVALID_ORDER;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderFindResponse findOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .filter(p -> p.getDeletedAt() == null)
                .map(OrderFindResponse::fromEntity)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));

    }

    public Page<OrderFindResponse> findAllOrders(OrderSearchRequest searchDto, Pageable pageable, String role, String userId) {
        return orderRepository.searchOrders(searchDto, pageable,role, userId);
    }

    public OrderCreateResponse createStore(OrderCreateRequest requestDto) {
        Order order = OrderCreateRequest.toEntity(requestDto);
        return OrderCreateResponse.fromEntity(orderRepository.save(order));
    }

    @Transactional
    public UUID deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));
        order.deleteOrder("user");
        return orderRepository.save(order).getId();
    }

}
