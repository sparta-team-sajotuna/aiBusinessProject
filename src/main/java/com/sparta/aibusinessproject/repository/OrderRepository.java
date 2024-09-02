package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, OrderRepositoryCustom {
    @Modifying
    @Query("UPDATE Order a SET a.deletedAt = CURRENT_TIMESTAMP, a.deletedBy = :deletedBy WHERE a.id = :orderId")
    void delete(@Param("orderId") UUID orderId, @Param("deletedBy") String deletedBy);
}
