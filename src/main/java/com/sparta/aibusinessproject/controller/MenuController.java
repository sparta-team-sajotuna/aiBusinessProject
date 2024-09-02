package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.MenuCreateRequest;
import com.sparta.aibusinessproject.domain.request.MenuModifyRequest;
import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.response.MenuCreateResponse;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import com.sparta.aibusinessproject.domain.response.MenuUpdateResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/menus")
@Tag(name = "Menu API", description = "메뉴 CRUD")
public class MenuController {

    private static final int[] ALLOWED_PAGE_SIZES = {10, 30, 50};
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final MenuService menuService;

    /**
     * 메뉴 단건 조회
     *
     * @param menuId
     * @return
     */
    @GetMapping("/{menuId}")
    @Operation(summary = "메뉴 단건 조회", description = "해당 가게의 해당 메뉴에 대한 상세 조회")
    public Response<MenuFindResponse> findMenu(@PathVariable UUID storeId,
                                               @PathVariable UUID menuId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(menuService.findMenu(storeId, menuId, userDetails.getUser()));
    }

    /**
     * 메뉴 전체 조회
     *
     * @param storeId
     * @param searchDto
     * @param pageable
     * @return
     */
    @GetMapping
    @Operation(summary = "메뉴 전체 조회", description = "해당 가게에 대한 메뉴 조회")
    public Response<Page<MenuFindResponse>> findMenus(@PathVariable UUID storeId,
                                                      MenuSearchRequest searchDto,
                                                      Pageable pageable,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        int size = DEFAULT_PAGE_SIZE; // 기본 10건
        if (Arrays.stream(ALLOWED_PAGE_SIZES).anyMatch(s -> s == pageable.getPageSize())) { //요청 사이즈가 10, 30, 50일 때
            size = pageable.getPageSize();
        }

        Pageable validatedPageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());

        return Response.success(menuService.findAllMenus(storeId, searchDto, validatedPageable, userDetails.getUser()));
    }

    /**
     * 메뉴 생성
     *
     * @param storeId
     * @param requestDto
     * @return
     */
    @PostMapping
    @Operation(summary = "메뉴 생성", description = "해당 가게에 대한 메뉴 생성")
    public Response<MenuCreateResponse> createMenu(@PathVariable UUID storeId,
                                                   @RequestBody @Valid MenuCreateRequest requestDto,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(menuService.createMenu(storeId, requestDto, userDetails.getUser()));
    }

    /**
     * 메뉴 수정
     *
     * @param storeId
     * @param menuId
     * @param requestDto
     * @return
     */
    @PatchMapping("/{menuId}")
    @Operation(summary = "메뉴 수정", description = "해당 가게에 대한 메뉴 수정")
    public Response<MenuUpdateResponse> modifyMenu(@PathVariable UUID storeId,
                                                   @PathVariable UUID menuId,
                                                   @RequestBody MenuModifyRequest requestDto,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(menuService.modifyMenu(storeId, menuId, requestDto, userDetails.getUser()));
    }

    /**
     * 메뉴 삭제
     *
     * @param storeId
     * @param menuId
     * @return
     */
    @DeleteMapping("/{menuId}")
    @Operation(summary = "메뉴 삭제", description = "해당 가게에 대한 메뉴 삭제")
    public Response<?> deleteMenu(@PathVariable UUID storeId,
                                  @PathVariable UUID menuId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        menuService.deleteMenu(storeId, menuId, userDetails.getUser());
        return Response.success("해당 메뉴 정보가 삭제되었습니다.");
    }
}

