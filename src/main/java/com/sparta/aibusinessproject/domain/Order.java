package com.sparta.aibusinessproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "p_order")
public class Order extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String storeName;

    private String paymentMethod;

    private int totalPrice;

    private String requests;

    @Enumerated(value = EnumType.STRING)
    private OrderStatusEnum status;

    private LocalDateTime deletedAt;
    private String deletedBy;

    /* TODO 유저랑 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;*/


    /* TODO 가게랑 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Store store;*/

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    public void deleteOrder(String deletedBy){
        this.deletedBy = deletedBy;
        this.deletedAt = LocalDateTime.now();
        modifyOrderStatus(OrderStatusEnum.CANCELLED);
    }

    public void addOrderMenu(OrderMenu menu) {
        orderMenuList.add(menu);
    }

    public void modifyOrderStatus(OrderStatusEnum status){
        this.status = status;
    }
}
