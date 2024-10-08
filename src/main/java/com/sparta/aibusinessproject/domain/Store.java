package com.sparta.aibusinessproject.domain;


import com.sparta.aibusinessproject.domain.request.StoreUpdateRequest;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "p_store")
@Builder
// Delete의 값이 null인 정보만 가져옴
@Where(clause = "deleted_at is NULL")
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

    private LocalTime openTiME;

    private LocalTime closeTiME;

    private String closedDays;          // 휴무일

    private String deliveryAddress;     // 배달지역


    @OneToMany(mappedBy = "store" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StoreCategory> storeCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "store" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Menu> menu;

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
        this.openTiME = request.openTime() != null ? request.openTime() : this.openTiME;
        this.closeTiME = request.closeTime() != null ? request.closeTime() : this.closeTiME;
        this.closedDays = request.closedDays() != null ? request.closedDays() : this.closedDays;
        this.deliveryAddress = request.deliveryAddress() != null ? request.deliveryAddress() : this.deliveryAddress;
    }

    public void categoryUpdate(StoreCategory storeCategory){
        storeCategories.add(storeCategory);
    }
}
