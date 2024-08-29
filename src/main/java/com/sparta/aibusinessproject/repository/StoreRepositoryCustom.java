package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.request.StoreSearchListRequest;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryCustom {
    Page<StoreSearchListResponse> searchStores(StoreSearchListRequest searchDto, Pageable pageable);
}
