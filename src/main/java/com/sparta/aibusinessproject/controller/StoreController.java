package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.*;
import com.sparta.aibusinessproject.domain.response.StoreCreateResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Tag(name = "Store API", description = "가게 CRUD")
public class StoreController {

    private static final int[] ALLOWED_PAGE_SIZES = {10, 30, 50};
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final StoreService storeService;

    // 가게 생성
    @PostMapping
    @Operation(summary = "가게 생성", description = "유저에 대한 가게 생성(가게 세부내용 작성)", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<StoreCreateResponse> store(@RequestBody StoreCreateRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return  Response.success(storeService.createOrder(request,userDetails.getUser()));

    }

    // 가게 AI 생성
    @PostMapping("/ai")
    @Operation(summary = "가게 AI 생성", description = "유저에 대한 가게 생성(ai를 활용한 작성), Content에 AI에게 할 질문을 넣어주세요", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<?> storeCreateByAi(@RequestBody StoreCreateRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return  Response.success(storeService.createOrderByAi(request,userDetails.getUser()) +"가게의 정보가 생성되었습니다");

    }

    // 가게 상세 조회
    @GetMapping("/{storeId}")
    @Operation(summary = "가게 세부 조회", description = "특정 가게에 대한 데이터 조회")
    public Response<StoreSearchResponse> getStore(@PathVariable UUID storeId) {
        return Response.success(storeService.getStoreById(storeId));
    }

    // 가게 리스트 전부 출력
    @PostMapping("/list")
    @Operation(summary = "가게 전체 조회", description = "모든 가게에 대한 데이터 조회")
    public Response<Page<StoreSearchListResponse>> getAllStores(@RequestBody StoreSearchListRequest searchDto, Pageable pageable) {
        int size = DEFAULT_PAGE_SIZE; // 기본 10건
        if(Arrays.stream(ALLOWED_PAGE_SIZES).anyMatch(s->s == pageable.getPageSize())){ //요청 사이즈가 10, 30, 50일 때
            size = pageable.getPageSize();
        }

        Pageable validatedPageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());


        return Response.success(storeService.getStores(searchDto,validatedPageable));
    }

    // 가게 수정
    @PatchMapping("/{storeId}")
    @Operation(summary = "가게 수정", description = "특정 가게에 대한 데이터 수정", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<StoreSearchResponse> storeUpdate(@PathVariable UUID storeId , @RequestBody StoreUpdateRequest request,  @AuthenticationPrincipal UserDetailsImpl userDetails){

        return  Response.success(storeService.update(storeId,request,userDetails.getUser()));
    }

    // 가게 삭제
    @DeleteMapping("/{storeId}")
    @Operation(summary = "가게 삭제", description = "특정 가게 삭제", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<?> storeDelete(@PathVariable UUID storeId,  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UUID  uuid = storeService.delete(storeId,userDetails.getUser());
        return Response.success( uuid +"가게 정보가 삭제되었습니다.");
    }


    // 가게주인 카테고리 추가
    @PostMapping("/{storeId}/category")
    @Operation(summary = "가게 카테고리 추가", description = "특정 가게에 대한 카테고리 추가", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<StoreSearchResponse> createStoreCategory(@PathVariable UUID storeId, @RequestBody CategoryListCreateRequest request,  @AuthenticationPrincipal UserDetailsImpl userDetails) {

//        return Response.success(storeService.createStoreCategory(storeId,request));
        return Response.success(storeService.createStoreCategory(storeId,request,userDetails.getUser()));
    }

    // 가게주인 카테고리 삭제
    @PostMapping("/{storeId}/categoryDelete")
    @Operation(summary = "가게 카테고리 삭제", description = "특정 가게에 대한 카테고리 삭제", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<?> deleteStoreCategory(@PathVariable UUID storeId, @RequestBody CategoryListCreateRequest request,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(storeService.deleteStoreCategory(storeId,request,userDetails.getUser()));
    }

}
