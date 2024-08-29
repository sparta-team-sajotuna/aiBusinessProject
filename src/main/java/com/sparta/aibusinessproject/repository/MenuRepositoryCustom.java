package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MenuRepositoryCustom {
    Page<MenuFindResponse> searchMenus(UUID storeId, MenuSearchRequest searchDto, Pageable pageable);
}
