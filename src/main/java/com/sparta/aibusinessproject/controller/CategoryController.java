package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.CategoryCreateRequest;
import com.sparta.aibusinessproject.domain.response.CategoryListResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.CategoryService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "카테고리 CRUD")
public class CategoryController {

    private static final int[] ALLOWED_PAGE_SIZES = {10, 30, 50};
    private static final int DEFAULT_PAGE_SIZE = 10;
    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "카테고리 추가", description = "유연한 카테고리 추가 기능", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<?> addFolders(@RequestBody CategoryCreateRequest request) {
        // TODO : UserName 추가
        categoryService.addCategory(request);

        return Response.success(request.name() +"의 카테고리 생성이 완료되었습니다.");
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "카테고리 세부 조회", description = "카테고리 ID값에 따른 상세 데이터 조회", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<CategoryListResponse> getCategory(@PathVariable UUID categoryId) {
        return Response.success(categoryService.getCategory(categoryId));
    }

    @GetMapping
    @Operation(summary = "카테고리 전체 조회", description = "모든 카테고리 리스트 조회", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<Page<CategoryListResponse>> getAllCategories(Pageable pageable) {

        int size = DEFAULT_PAGE_SIZE; // 기본 10건
        if(Arrays.stream(ALLOWED_PAGE_SIZES).anyMatch(s->s == pageable.getPageSize())){ //요청 사이즈가 10, 30, 50일 때
            size = pageable.getPageSize();
        }

        Pageable validatedPageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
        return Response.success(categoryService.getCategories(validatedPageable));
    }

    @PatchMapping("/{categoryId}")
    @Operation(summary = "카테고리 수정", description = "카테고리 ID값에 따른 데이터 수정", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<CategoryListResponse> updateCategory(@PathVariable UUID categoryId, @RequestBody CategoryCreateRequest request) {
        return Response.success(categoryService.update(categoryId,request));
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "카테고리 삭제", description = "카테고리 ID값에 따른 데이터 삭제", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<?> deleteCategory(@PathVariable UUID categoryId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        categoryService.delete(categoryId,userDetails.getUser());
        return Response.success(categoryId +"의 삭제가 완료되었습니다.");
    }
}
