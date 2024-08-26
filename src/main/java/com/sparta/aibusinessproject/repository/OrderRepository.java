package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
