package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.*;
import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import com.sparta.aibusinessproject.domain.request.OrderMenuRequest;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.OrderCreateResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.repository.MenuRepository;
import com.sparta.aibusinessproject.repository.OrderRepository;
import com.sparta.aibusinessproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.sparta.aibusinessproject.exception.ErrorCode.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final MenuService menuService;

    @Transactional(readOnly = true)
    public OrderFindResponse findOrder(UUID orderId, User user) {
        return orderRepository.findById(orderId)
                .map(OrderFindResponse::fromEntity)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));

    }

    @Transactional(readOnly = true)
    public Page<OrderFindResponse> findAllOrders(OrderSearchRequest searchDto, Pageable pageable, String role, String userId) {
        return orderRepository.searchOrders(searchDto, pageable,role, userId);
    }

    @Transactional
    public OrderCreateResponse createStore(OrderCreateRequest requestDto, User user) {
        // (1) 접근 권한 검증
        if(!user.getRole().equals(UserRoleEnum.CUSTOMER) && !user.getRole().equals(UserRoleEnum.OWNER)){
            throw new ApplicationException(ACCESS_DENIED);
        }

        // (2) 가게 검증
        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new ApplicationException(INVALID_STORE));

        Order order = OrderCreateRequest.toEntity(requestDto, store, user);

        // (3) orderMenu 검증
        for(OrderMenuRequest orderMenuRequest : requestDto.getMenuIds()){
            Menu menu = menuRepository.findById(orderMenuRequest.getMenuId())
                    .orElseThrow(() -> new ApplicationException(INVALID_MENU));

            // 요청 메뉴가 가게에 있는지 검증
            if(!menu.getStore().getId().equals(store.getId())){
                throw new ApplicationException(NOTFOUND_MENU);
            }

            // 메뉴 수량 검증
            menuService.reduceMenuQuantity(menu.getId(), orderMenuRequest.getQuantity());
            OrderMenu orderMenu = OrderMenu.createOrderMenu(order, menu, orderMenuRequest.getQuantity());

            // orderMenu 추가 및 totalPrice 계산
            order.addOrderMenuAndTotalPrice(orderMenu);
        }

        // 최소 주문금액 검증
        if(order.getTotalPrice() > store.getMinDeliveryPrice()){
            throw new ApplicationException(EXCEED_TOTAL_PRICE);
        }

        //TODO
        // 배달 지역 검증 ..어떻게하지?
        // 휴무일 검증 ..

        return OrderCreateResponse.fromEntity(orderRepository.save(order));
    }

    @Transactional
    public UUID deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
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

    @Transactional
    public UUID modifyOrderStatus(UUID orderId, OrderStatusEnum status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(INVALID_ORDER));

        order.modifyOrderStatus(status);
        return orderRepository.save(order).getId();
    }

    // 수량 검증 메서드
    private void validateQuantity(int availableQuantity, int requestedQuantity) {
        if (availableQuantity < requestedQuantity) {
            throw new ApplicationException(INVALID_QUANTITY);
        }
    }
}
