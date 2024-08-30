package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Store;
import com.sparta.aibusinessproject.domain.StoreCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor

public class StoreSearchResponse {


    private String name;
    private String content;
    private int minDeliveryPrice;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String closedDays;
    private String deliveryAddress;


    private List<CategoryListResponse> categories = new ArrayList<>();


    // entity -> searchDto  새로 생성하는 경우
    public StoreSearchResponse(Store store) {
        this.name = store.getName();
        this.content = store.getContent();
        this.minDeliveryPrice = store.getMinDeliveryPrice();
        this.openTime = store.getOpenTiME();
        this.closeTime = store.getCloseTiME();
        this.closedDays = store.getClosedDays();
        this.deliveryAddress = store.getDeliveryAddress();
        for(StoreCategory storeCategory : store.getStoreCategories()){
            categories.add(CategoryListResponse.from(storeCategory.getCategory()));
        }
    }

}
