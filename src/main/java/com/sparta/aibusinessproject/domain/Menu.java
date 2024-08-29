package com.sparta.aibusinessproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.aibusinessproject.domain.request.MenuModifyRequest;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "p_menu")
public class Menu extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    public void modifyMenu(MenuModifyRequest requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
    }

    public void deleteMenu(String deletedBy){
        this.setDeletedAt(LocalDateTime.now());
        this.setDeletedBy(deletedBy);
    }

    public void reduceQuantity(int quantity) {
        this.quantity = this.quantity-quantity;
    }
}
