package com.sparta.aibusinessproject.domain;

import com.sparta.aibusinessproject.domain.dto.StoreCategoryDto;
import com.sparta.aibusinessproject.domain.dto.StoreDto;
import com.sparta.aibusinessproject.domain.request.StoreUpdateRequest;
import com.sparta.aibusinessproject.domain.response.CategoryListResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "p_store")
@Builder
// Delete의 값이 null인 정보만 가져옴
@Where(clause = "deleted_at is NULL")
// Delete 쿼리문이 동작될때, 실제로는 Delete쿼리문이 가지않고 아래의 쿼리문이 동작함
@SQLDelete(sql = "UPDATE p_store SET deleted_at = current_timestamp WHERE id = ?")
public class Store extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;                // 가게 이름

    @Column(nullable = false)
    private String address;             // 가게 주소

    @Column(nullable = false)
    private String phone;              // 가게 번호

    private String content;

    @Column(nullable = false)
    private int minDeliveryPrice;               // 최소 주문 금액

    private String operationHours;      // 운영시간

    private String closedDays;          // 휴무일

    private String deliveryAddress;     // 배달지역

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "store" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StoreCategory> storeCategories;

    // TODO : Menu Entity 연동되면 List 연결
    // User user

    // DTO로 변환하는 메서드
    public StoreSearchListResponse toResponseDto() {
        return new StoreSearchListResponse(
                name,
                minDeliveryPrice
        );
    }

    public void update(StoreUpdateRequest request) {
        this.name = request.name() != null ? request.name() : this.name;
        this.address = request.address() != null ? request.address() : this.address;
        this.phone = request.phone() != null ? request.phone() : this.phone;
        this.content = request.content() != null ? request.content() : this.content;
        this.minDeliveryPrice = request.minDeliveryPrice() != 0 ? request.minDeliveryPrice() : this.minDeliveryPrice;
        this.operationHours = request.operationHousrs() != null ? request.operationHousrs() : this.operationHours;
        this.closedDays = request.closedDays() != null ? request.closedDays() : this.closedDays;
        this.deliveryAddress = request.deliveryAddress() != null ? request.deliveryAddress() : this.deliveryAddress;
    }

    public void categoryUpdate(StoreCategory storeCategory){
        storeCategories.add(storeCategory);
    }
}
