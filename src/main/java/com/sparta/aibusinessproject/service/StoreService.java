package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Category;
import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.StoreCategory;
import com.sparta.aibusinessproject.domain.dto.StoreDto;
import com.sparta.aibusinessproject.domain.request.*;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.CategoryRepository;
import com.sparta.aibusinessproject.repository.StoreCategoryRepository;
import com.sparta.aibusinessproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final CategoryService categoryService;

    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final StoreCategoryRepository storeCategoryRepository;

    // 가게 추가
    @Transactional
    public String createOrder(StoreCreateRequest requestDto) {
        StoreDto dto = StoreCreateRequest.toDto(requestDto);

        // 존재하는 가게이름인지 확인
        Optional<Store> store = storeRepository.findByName(dto.name());

        // 존재하지 않다면
        if(!store.isEmpty()) {
            throw new ApplicationException(ErrorCode.DUPLICATED_STORENAME);
        }
        Store storeEntity = storeRepository.save(StoreDto.toEntity(dto));

        return storeEntity.getName();
    }

    // 가게 리스트 출력
    public Page<StoreSearchListResponse> getStores(StoreSearchListRequest searchDto, Pageable pageable) {
        Optional<Category> category = categoryRepository.findByName(searchDto.category());

        UUID categoryId;

        if(category.isEmpty()){
            categoryId = null;
        }else{
            categoryId = category.get().getId();
        }

        return storeRepository.searchStores(searchDto, pageable , categoryId);
    }

    // 가게 세부 조회
    @Transactional
    public StoreSearchResponse getStoreById(UUID storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

            return  new StoreSearchResponse(store);
    }

    // 가게 수정
    @Transactional
    public StoreDto update(UUID storeId, StoreUpdateRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        store.update(request);

        return  StoreDto.from(store);
    }

    // 가게 삭제
    @Transactional
    public void delete(UUID storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        storeRepository.delete(store);
    }

    // 가게에 대한 카테고리 생성
    @Transactional
    public void createStoreCategory(UUID storeId, CategoryListCreateRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        List<StoreCategory> storeCategories;

        // StoreCategory가 존재하는지 확인, 존재하지 않다면 새 리스트 생성 / 존재한다면 기존 리스트 가져옴
        if(store.getStoreCategories().isEmpty()){
            storeCategories = new ArrayList<>();
        }else{
            storeCategories = store.getStoreCategories();
        }


        // 카테고리 신규 생성 / 기존 값 호출
        Optional<Category> category = categoryRepository.findByName(request.categories());
        Category categoryEntity;

        if(category.isEmpty()) {
            categoryEntity = categoryService.addCategory(new CategoryCreateRequest(request.categories()));
        }else{
            categoryEntity = category.get();
        }

        // StoreCategory Entity 생성

        // 이미 존재하는 가게에 대한 카테고리인지 확인
        for(StoreCategory storeCategory : storeCategories){
            if(storeCategory.getCategory().getId().equals(categoryEntity.getId())){
                throw new ApplicationException(ErrorCode.ALREADY_CATEGORY);
            }
        }
        StoreCategory storeCategory = storeCategoryRepository.save(new StoreCategory(categoryEntity,store));
        store.categoryUpdate(storeCategory);
    }
}
