package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID>, PaymentRepositoryCustom {
    boolean existsByOrderId(UUID orderId);
}
