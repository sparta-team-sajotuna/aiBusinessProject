package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import com.sparta.aibusinessproject.domain.response.OrderCreateResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Response<OrderFindResponse> findOrder(@RequestParam UUID orderId){
        return Response.success(orderService.findOrder(orderId));
    }

    @PostMapping
    public Response<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest requestDto) {
        return Response.success(orderService.createStore(requestDto));
    }

    @DeleteMapping("/{orderId}")
    public Response<UUID> deleteOrder(@PathVariable UUID orderId){
        return Response.success(orderService.deleteOrder(orderId));
    }

}

