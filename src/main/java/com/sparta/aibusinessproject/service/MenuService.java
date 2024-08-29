package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.Store;
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
    public MenuFindResponse findMenu(UUID storeId, UUID menuId) {
        return menuRepository.findById(menuId)
                .filter(p -> p.getDeletedAt() == null && p.getStore().getId().equals(storeId))
                .map(MenuFindResponse::fromEntity)
                .orElseThrow(() -> new ApplicationException(INVALID_MENU));

    }

    @Transactional(readOnly = true)
    public Page<MenuFindResponse> findAllMenus(UUID storeId, MenuSearchRequest searchDto, Pageable pageable) {
        return menuRepository.searchMenus(storeId, searchDto, pageable);
    }

    @Transactional
    public MenuCreateResponse createMenu(UUID storeId, MenuCreateRequest requestDto) {

        Menu menu = MenuCreateRequest.toEntity(requestDto,
                storeRepository.findById(storeId)
                        .filter(p ->p.getDeletedAt() == null)
                        .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_MENU)) //TODO :: INVALID_STORE로 변경하기
        );

        return MenuCreateResponse.fromEntity(menuRepository.save(menu));

    }

    @Transactional
    public MenuUpdateResponse modifyMenu(UUID storeId, UUID menuId, MenuModifyRequest requestDto) {

        Menu menu = menuRepository.findById(menuId)
                .filter(p->p.getDeletedAt() == null && p.getStore().getId().equals(storeId))
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_MENU)
        );

        menu.modifyMenu(requestDto);
        return MenuUpdateResponse.fromEntity(menuRepository.save(menu));
    }

    @Transactional
    public UUID deleteMenu(UUID storeId, UUID menuId) {

        Menu menu = menuRepository.findById(menuId)
                .filter(p->p.getDeletedAt() == null && p.getStore().getId().equals(storeId))
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_MENU)
                );

        // TODO 하드코딩 수정
        menu.deleteMenu("username");
        return menuRepository.save(menu).getId();
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
