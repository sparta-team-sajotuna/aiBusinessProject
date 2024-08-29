package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.OrderCreateRequest;
import com.sparta.aibusinessproject.domain.response.OrderCreateResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/stores")
//public class OrderController {
//
//    private final OrderService orderService;
//
//    @PostMapping
//    public Response<OrderCreateResponse> createStore(@RequestBody OrderCreateRequest request){
//
//
//        return Response.success(new OrderCreateResponse());
//    }
//}

