package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.Address;
import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import com.sparta.aibusinessproject.domain.request.OrderModifyRequest;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.OrderCreateResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 단건 주문
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public Response<OrderFindResponse> findOrder(@PathVariable UUID orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(orderService.findOrder(orderId, userDetails.getUser()));
    }

    @GetMapping
    public Response<Page<OrderFindResponse>> findOrders(OrderSearchRequest searchDto, @PageableDefault(size = 10) Pageable pageable){
        //TODO 안지연
        // - 하드코딩 수정
        String role = "CUSTOMER";
        String userId = "user";
        return Response.success(orderService.findAllOrders(searchDto, pageable, role, userId));
    }

    /**
     * 주문 생성
     * @param requestDto
     * @return
     */
    @PostMapping
    public Response<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(orderService.createStore(requestDto, userDetails.getUser()));
    }

    @DeleteMapping("/{orderId}")
    public Response<UUID> deleteOrder(@PathVariable UUID orderId){
        return Response.success(orderService.deleteOrder(orderId));
    }

    @PatchMapping("/{orderId}")
    public Response<UUID> modify(@PathVariable UUID orderId, @RequestBody OrderModifyRequest requestDto){
        return Response.success(orderService.modifyOrderStatus(orderId, requestDto.getStatus()));
    }

}


