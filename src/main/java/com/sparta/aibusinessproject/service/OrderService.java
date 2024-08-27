package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderMenu;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import com.sparta.aibusinessproject.domain.request.OrderMenuRequest;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.OrderCreateResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.repository.MenuRepository;
import com.sparta.aibusinessproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.sparta.aibusinessproject.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public OrderFindResponse findOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .filter(p -> p.getDeletedAt() == null)
                .map(OrderFindResponse::fromEntity)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));

    }

    @Transactional(readOnly = true)
    public Page<OrderFindResponse> findAllOrders(OrderSearchRequest searchDto, Pageable pageable, String role, String userId) {
        return orderRepository.searchOrders(searchDto, pageable,role, userId);
    }

    @Transactional
    public OrderCreateResponse createStore(OrderCreateRequest requestDto) {
        Order order = OrderCreateRequest.toEntity(requestDto);

        for(OrderMenuRequest orderMenu : requestDto.getMenuIds()){
            order.addOrderMenu(OrderMenu.createOrderMenu(order,
                    menuRepository.findById(orderMenu.getMenuId())
                    .filter(p -> p.getDeletedAt() == null)
                    .orElseThrow(() -> new ApplicationException(INVALID_MENU)), orderMenu.getQuantity())
            );
        }

        return OrderCreateResponse.fromEntity(orderRepository.save(order));
    }

    @Transactional
    public UUID deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));

        //5분 이내 취소
        LocalDateTime createdAt = order.getCreatedAt();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createdAt, now);
        if(duration.toMinutes() > 5){
            throw new ApplicationException(ORDER_CANCELLATION_NOT_ALLOWED_TIME);
        }

        //TODO 하드코딩 수정
        order.deleteOrder("user");

        return orderRepository.save(order).getId();
    }

    public UUID modifyOrderStatus(UUID orderId, OrderStatusEnum status) {
        Order order = orderRepository.findById(orderId)
                .filter(p -> p.getDeletedAt() == null)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));

        order.modifyOrderStatus(status);
        return orderRepository.save(order).getId();
    }
}
