package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.request.StoreSearchListRequest;
import com.sparta.aibusinessproject.domain.response.CategoryListResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryRepositoryCustom {
    Page<CategoryListResponse>  searchCategories(Pageable pageable);
}
