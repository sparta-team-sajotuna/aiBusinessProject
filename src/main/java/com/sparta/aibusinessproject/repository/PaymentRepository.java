package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.Payment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID>, PaymentRepositoryCustom {
    boolean existsByOrderId(UUID orderId);
    Optional<Payment> findByOrderId(UUID orderId);
    @Modifying
    @Query("UPDATE Payment a SET a.deletedAt = CURRENT_TIMESTAMP, a.deletedBy = :deletedBy WHERE a.id = :paymentId")
    void delete(@Param("paymentId") UUID paymentId, @Param("deletedBy") String deletedBy);
}
