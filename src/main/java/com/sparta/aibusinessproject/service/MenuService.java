package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.MenuCreateRequest;
import com.sparta.aibusinessproject.domain.request.MenuModifyRequest;
import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.MenuCreateResponse;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import com.sparta.aibusinessproject.domain.response.MenuUpdateResponse;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.MenuRepository;
import com.sparta.aibusinessproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.sparta.aibusinessproject.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public MenuFindResponse findMenu(UUID storeId, UUID menuId, User user) {
        // 접근권한 검증
        if(user.getRole().equals(UserRoleEnum.MASTER)){
            throw new ApplicationException(ACCESS_DENIED);
        }

        // 가게 검증
        storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(INVALID_STORE));

        // 메뉴 검증
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApplicationException(INVALID_MENU));

        // 가게 내 메뉴 검증
        if(!menu.getStore().getId().equals(storeId)){
            throw new ApplicationException(NOTFOUND_MENU);
        }

        return MenuFindResponse.fromEntity(menu);

    }

    @Transactional(readOnly = true)
    public Page<MenuFindResponse> findAllMenus(UUID storeId, MenuSearchRequest searchDto, Pageable pageable, User user) {
        if(user.getRole().equals(UserRoleEnum.MASTER)){
            throw new ApplicationException(ACCESS_DENIED);
        }
        // 가게 검증
        storeRepository.findById(storeId)
                .orElseThrow(() -> new ApplicationException(INVALID_STORE));

        return menuRepository.searchMenus(storeId, searchDto, pageable);
    }

    @Transactional
    public MenuCreateResponse createMenu(UUID storeId, MenuCreateRequest requestDto, User user) {
        if(user.getRole().equals(UserRoleEnum.MASTER) || user.getRole().equals(UserRoleEnum.CUSTOMER)){
            throw new ApplicationException(ACCESS_DENIED);
        }

        if(user.getRole().equals(UserRoleEnum.OWNER)){ //가게 주인이면 본인가게를 검증하고 관리자면 검증을 생략한다.
            // 본인 가게 검증
            Store store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new ApplicationException(INVALID_STORE));

            if(!store.getUser().getUserId().equals(user.getUserId())){
                throw new ApplicationException(ACCESS_DENIED);
            }
        }

        Menu menu = MenuCreateRequest.toEntity(requestDto,
                storeRepository.findById(storeId)
                        .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_STORE))
        );

        return MenuCreateResponse.fromEntity(menuRepository.save(menu));

    }

    @Transactional
    public MenuUpdateResponse modifyMenu(UUID storeId, UUID menuId, MenuModifyRequest requestDto, User user) {
        if(user.getRole().equals(UserRoleEnum.MASTER) || user.getRole().equals(UserRoleEnum.CUSTOMER)){
            throw new ApplicationException(ACCESS_DENIED);
        }

        if(user.getRole().equals(UserRoleEnum.OWNER)){ //가게 주인이면 본인가게를 검증하고 관리자면 검증을 생략한다.
            // 본인 가게 검증
            Store store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new ApplicationException(INVALID_STORE));

            if(!store.getUser().getUserId().equals(user.getUserId())){
                throw new ApplicationException(ACCESS_DENIED);
            }
        }

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_MENU));

        // 가게 내 메뉴 검증
        if(!menu.getStore().getId().equals(storeId)){
            throw new ApplicationException(NOTFOUND_MENU);
        }

        menu.modifyMenu(requestDto);
        return MenuUpdateResponse.fromEntity(menuRepository.save(menu));
    }

    @Transactional
    public void deleteMenu(UUID storeId, UUID menuId, User user) {
        if(user.getRole().equals(UserRoleEnum.MASTER) || user.getRole().equals(UserRoleEnum.CUSTOMER)){
            throw new ApplicationException(ACCESS_DENIED);
        }

        if(user.getRole().equals(UserRoleEnum.OWNER)){ //가게 주인이면 본인가게를 검증하고 관리자면 검증을 생략한다.
            // 본인 가게 검증
            Store store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new ApplicationException(INVALID_STORE));

            if(!store.getUser().getUserId().equals(user.getUserId())){
                throw new ApplicationException(ACCESS_DENIED);
            }
        }

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_MENU));

        // 가게 내 메뉴 검증
        if(!menu.getStore().getId().equals(storeId)){
            throw new ApplicationException(NOTFOUND_MENU);
        }

        menuRepository.delete(menu);
    }

    @Transactional
    public void reduceMenuQuantity(UUID menuId, int quantity) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApplicationException(INVALID_MENU));

        if (menu.getQuantity() < quantity) {
            throw new ApplicationException(INVALID_QUANTITY);
        }

        menu.reduceQuantity(quantity);
        menuRepository.save(menu);
    }
}
