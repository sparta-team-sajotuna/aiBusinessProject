package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.response.AiSearchListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AiRepositoryCustom {
    Page<AiSearchListResponse> searchAi(int size, Pageable pageable);
}
