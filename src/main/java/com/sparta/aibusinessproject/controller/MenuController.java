package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.request.*;
import com.sparta.aibusinessproject.domain.response.MenuCreateResponse;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import com.sparta.aibusinessproject.domain.response.MenuUpdateResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/menus")
public class MenuController {

    private final MenuService menuService;

    /**
     * 메뉴 단건 조회
     * @param menuId
     * @return
     */
    @GetMapping("/{menuId}")
    public Response<MenuFindResponse> findMenu(@PathVariable UUID storeId, @PathVariable UUID menuId){
        return Response.success(menuService.findMenu(storeId, menuId));
    }

    /**
     * 메뉴 전체 조회
     * @param storeId
     * @param searchDto
     * @param pageable
     * @return
     */
    @GetMapping
    public Response<Page<MenuFindResponse>> findMenus(@PathVariable UUID storeId, MenuSearchRequest searchDto, @PageableDefault(size = 10) Pageable pageable){
        return Response.success(menuService.findAllMenus(storeId, searchDto, pageable));
    }

    /**
     * 메뉴 생성
     * @param storeId
     * @param requestDto
     * @return
     */
    @PostMapping
    public Response<MenuCreateResponse> createMenu(@PathVariable UUID storeId, @RequestBody MenuCreateRequest requestDto) {
        return Response.success(menuService.createStore(storeId, requestDto));
    }

    /**
     * 메뉴 수정
     * @param storeId
     * @param menuId
     * @param requestDto
     * @return
     */
    @PatchMapping("/{menuId}")
    public Response<MenuUpdateResponse> modifyMenu(@PathVariable UUID storeId, @PathVariable UUID menuId, @RequestBody MenuModifyRequest requestDto){
        return Response.success(menuService.modifyStore(storeId,menuId,requestDto));
    }

    /**
     * 메뉴 삭제
     * @param storeId
     * @param menuId
     * @return
     */
//    @DeleteMapping("/{menuId}")
//    public Response<UUID> deleteMenu(@PathVariable UUID storeId, @PathVariable UUID menuId){
//        return Response.success(menuService.deleteStore(storeId,menuId));
//    }
}

