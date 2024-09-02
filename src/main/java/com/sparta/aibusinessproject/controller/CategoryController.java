package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.CategoryCreateRequest;
import com.sparta.aibusinessproject.domain.response.CategoryListResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "카테고리 CRUD")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "카테고리 추가", description = "유연한 카테고리 추가 기능")
    public Response<?> addFolders(@RequestBody CategoryCreateRequest request) {
        // TODO : UserName 추가
        categoryService.addCategory(request);

        return Response.success(request.name() +"의 카테고리 생성이 완료되었습니다.");
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "카테고리 세부 조회", description = "카테고리 ID값에 따른 상세 데이터 조회")
    public Response<CategoryListResponse> getCategory(@PathVariable UUID categoryId) {
        return Response.success(categoryService.getCategory(categoryId));
    }

    @GetMapping
    @Operation(summary = "카테고리 전체 조회", description = "모든 카테고리 리스트 조회")
    public Response<List<CategoryListResponse>> getAllCategories() {
        return Response.success(categoryService.getCategories());
    }

    @PatchMapping("/{categoryId}")
    @Operation(summary = "카테고리 수정", description = "카테고리 ID값에 따른 데이터 수정")
    public Response<CategoryListResponse> updateCategory(@PathVariable UUID categoryId, @RequestBody CategoryCreateRequest request) {
        return Response.success(categoryService.update(categoryId,request));
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "카테고리 삭제", description = "카테고리 ID값에 따른 데이터 삭제")
    public Response<?> deleteCategory(@PathVariable UUID categoryId) {
        categoryService.delete(categoryId);
        return Response.success(categoryId +"의 삭제가 완료되었습니다.");
    }
}
