package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.dto.StoreCategoryDto;
import com.sparta.aibusinessproject.domain.dto.StoreDto;
import com.sparta.aibusinessproject.domain.request.*;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping
    public Response<?> store(@RequestBody StoreCreateRequest request) {
        storeService.createOrder(request);
        return  Response.success("가게 생성에 성공하였습니다");
    }

    // 가게 상세 조회
    @GetMapping("/{storeId}")
    public Response<StoreSearchResponse> getStore(@PathVariable UUID storeId) {
        return Response.success(storeService.getStoreById(storeId));
    }

    // 가게 리스트 전부 출력
    @PostMapping("/list")
    public Response<Page<StoreSearchListResponse>> getAllStores(@RequestBody StoreSearchListRequest searchDto, Pageable pageable) {
        return Response.success(storeService.getStores(searchDto,pageable));
    }

    // 가게 수정
    @PatchMapping("/{storeId}")
    public Response<StoreDto> storeUpdate(@PathVariable UUID storeId , @RequestBody StoreUpdateRequest request){

        return  Response.success(storeService.update(storeId,request));
    }

    // 가게 삭제
    @DeleteMapping("/{storeId}")
    public Response<?> storeDelete(@PathVariable UUID storeId) {
        storeService.delete(storeId);
        return Response.success("가게 정보가 삭제되었습니다.");
    }


    // 가게주인 카테고리 추가
    @PostMapping("/{storeId}/category")
    public Response<?> createStoreCategory(@PathVariable UUID storeId, @RequestBody CategoryListCreateRequest request) {

//        return Response.success(storeService.createStoreCategory(storeId,request));
        storeService.createStoreCategory(storeId,request);
        return Response.success("성공");
    }

}
