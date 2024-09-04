package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Category;
import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.request.CategoryCreateRequest;
import com.sparta.aibusinessproject.domain.response.CategoryListResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    // 카테고리 추가
    @Transactional
    public Category addCategory(CategoryCreateRequest request) {
        // DB에 해당 기존 카테고리 리스트 찾기
        if(categoryRepository.existsByName(request.name())){
            throw new ApplicationException(ErrorCode.DUPLICATED_CATEGORY);
        }

        Category category = categoryRepository.save(Category.builder().name(request.name()).build());


        return category;
}
    
    // 카테고리 전부 출력
    public Page<CategoryListResponse> getCategories(Pageable pageable) {

        return categoryRepository.searchCategories(pageable);
    }

    // 카테고리 세부 출력
    public CategoryListResponse getCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOTFOUND_CATEGORY));

        return CategoryListResponse.from(category);
    }

    // 카테고리 업데이트
    @Transactional
    public CategoryListResponse update(UUID categoryId, CategoryCreateRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOTFOUND_CATEGORY));


        // 카테고리 중복 검사
        if(categoryRepository.existsByName(request.name())){
            throw new ApplicationException(ErrorCode.DUPLICATED_CATEGORY);
        }

        category.update(request);

        return CategoryListResponse.from(category);
    }
    
    // 카테고리 삭제
    @Transactional
    public void delete(UUID categoryId, User user) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOTFOUND_CATEGORY));

        categoryRepository.delete(category.getId(),user.getUserId());
    }
}
