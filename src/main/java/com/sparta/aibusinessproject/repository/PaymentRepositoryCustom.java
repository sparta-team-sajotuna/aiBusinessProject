package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.response.PaymentFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PaymentRepositoryCustom {
    Page<PaymentFindResponse> searchPayments(PaymentSearchRequest searchDto, Pageable pageable, UserRoleEnum role, String userId);
}
