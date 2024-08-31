package com.sparta.aibusinessproject.ai;

import com.sparta.aibusinessproject.domain.request.StoreSearchListRequest;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AiRepositoryCustom {
    Page<AiSearchListResponse> searchAi(int size, Pageable pageable);
}
