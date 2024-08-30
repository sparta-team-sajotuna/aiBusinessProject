package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.*;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StoreService {

    private final CategoryService categoryService;

    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final StoreCategoryRepository storeCategoryRepository;

    // 가게 추가
    @Transactional
    public String createOrder(StoreCreateRequest requestDto, User user) {

        // 유저 권한일때는 접근 권한 없음
        if(user.getRole() == UserRoleEnum.CUSTOMER){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        StoreDto dto = StoreCreateRequest.toDto(requestDto);

        // 존재하는 가게이름인지 확인
        Optional<Store> store = storeRepository.findByName(dto.name());

        // 존재하지 않다면
        if(!store.isEmpty()) {
            throw new ApplicationException(ErrorCode.DUPLICATED_STORENAME);
        }

        Store storeEntity = storeRepository.save(StoreDto.toEntity(dto,user));

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
    public StoreSearchResponse update(UUID storeId, StoreUpdateRequest request, User user) {
        // 유저 권한일때는 접근 권한 없음
        if(user.getRole() == UserRoleEnum.CUSTOMER){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        // 로그인한 유저와 가게 작성한 유저가 다를 경우
        if(!store.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        store.update(request);

        return  new StoreSearchResponse(store);
    }

    // 가게 삭제
    @Transactional
    public UUID delete(UUID storeId, User user) {
        // 유저 권한일때는 접근 권한 없음
        if(user.getRole() == UserRoleEnum.CUSTOMER){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        // 로그인한 유저와 가게 작성한 유저가 다를 경우
        if(!store.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        storeRepository.delete(store);

        return storeId;
    }

    // 가게에 대한 카테고리 생성
    @Transactional
    public StoreSearchResponse createStoreCategory(UUID storeId, CategoryListCreateRequest request, User user) {
        // 유저 권한일때는 접근 권한 없음
        if(user.getRole() == UserRoleEnum.CUSTOMER){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        // 로그인한 유저와 가게 작성한 유저가 다를 경우
        if(!store.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

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

        // 이미 가게에 대한 존재하는 카테고리인지 확인
        for(StoreCategory storeCategory : storeCategories){
            if(storeCategory.getCategory().getId().equals(categoryEntity.getId())){
                throw new ApplicationException(ErrorCode.ALREADY_CATEGORY);
            }
        }
        StoreCategory storeCategory = storeCategoryRepository.save(new StoreCategory(categoryEntity,store));
        store.categoryUpdate(storeCategory);


        return  new StoreSearchResponse(store);
    }

    // 가게에 대한 카테고리 삭제
    @Transactional
    public String deleteStoreCategory(UUID storeId, CategoryListCreateRequest request, User user) {
        // 유저 권한일때는 접근 권한 없음
        if(user.getRole() == UserRoleEnum.CUSTOMER){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE));

        // 로그인한 유저와 가게 작성한 유저가 다를 경우
        if(!store.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        List<StoreCategory> storeCategories;

        // StoreCategory가 존재하는지 확인, 존재하지 않다면 에러 발생 / 존재한다면 기존 리스트 가져옴
        if(store.getStoreCategories().isEmpty()){
            throw new ApplicationException(ErrorCode.NOTFOUND_CATEGORY);
        }


        // 카테고리 값 존재유무 확인 및 삭제
        Optional<Category> category = categoryRepository.findByName(request.categories());
        Category categoryEntity;

        if(category.isEmpty()) {
            throw new ApplicationException(ErrorCode.INVALID_CATEGORY);
        }else{
            categoryEntity = category.get();
            log.info(categoryEntity.getName() + "," + categoryEntity.getId());
            // 해당 storeCategory 삭제
            storeCategoryRepository.deleteByCategoryId(categoryEntity.getId());
        }

        return  request.categories()+"카테고리가 삭제되었습니다";
    }

}
