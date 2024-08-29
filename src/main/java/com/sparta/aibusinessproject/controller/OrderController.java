package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.Address;
import com.sparta.aibusinessproject.domain.User;
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
     * 주문 단건 조회
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public Response<OrderFindResponse> findOrder(@PathVariable UUID orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(orderService.findOrder(orderId, userDetails.getUser()));
    }

    @GetMapping
    public Response<Page<OrderFindResponse>> findOrders(OrderSearchRequest searchDto,
                                                        @PageableDefault(size = 10) Pageable pageable,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return Response.success(orderService.findAllOrders(searchDto, pageable, userDetails.getUser()));
    }

    /**
     * 주문 생성
     *
     * @param requestDto
     * @return
     */
    @PostMapping
    public Response<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(orderService.createStore(requestDto, userDetails.getUser()));
    }

    /**
     * 주문 취소
     *
     * @param orderId
     * @return
     */
    @PatchMapping("/{orderId}")
    public Response<UUID> cancelOrder(@PathVariable UUID orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(orderService.cancelOrder(orderId, userDetails.getUser()));
    }

    /**
     * 주문 상태 변경
     *
     * @param orderId
     * @param requestDto
     * @return
     */
    @PatchMapping("/{orderId}/status")
    public Response<UUID> modifyOrderStatus(@PathVariable UUID orderId, @RequestBody OrderModifyRequest requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(orderService.modifyOrderStatus(orderId, requestDto.getStatus(), userDetails.getUser()));
    }

    /**
     * 주문 삭제
     * @param orderId
     * @return
     */
    @DeleteMapping("{orderId}")
    public Response<?> deleteOrder(@PathVariable UUID orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.deleteOrder(orderId, userDetails.getUser());
        return Response.success("주문 정보가 삭제되었습니다.");
    }
}


