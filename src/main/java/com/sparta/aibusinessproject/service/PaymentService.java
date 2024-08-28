package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.Payment;
import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.request.PaymentCallbackRequest;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentCreateResponse;
import com.sparta.aibusinessproject.domain.response.PaymentFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentSearchRequest;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.OrderRepository;
import com.sparta.aibusinessproject.repository.PaymentRepository;
import com.sparta.aibusinessproject.repository.UserRepository;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.sparta.aibusinessproject.exception.ErrorCode.INVALID_MENU;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PaymentFindResponse findPayment(UUID paymentId, User user) {

        // 1) payment 조회
        Payment payment = paymentRepository.findById(paymentId)
                .filter(p->p.getDeletedAt() == null)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_PAYMENT));

        // 2) 회원 검증
        if(!payment.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.NOT_YOUR_PAYMENT);
        }

        return PaymentFindResponse.fromEntity(payment);

    }

    @Transactional(readOnly = true)
    public Page<PaymentFindResponse> findAllPayments(PaymentSearchRequest searchDto, Pageable pageable, User user) {
        return paymentRepository.searchPayments(searchDto, pageable, user.getRole(), user.getUserId());
    }

    @Transactional
    public PaymentCreateResponse createPayment(UUID orderId, PaymentCallbackRequest callbackRequest, User user) {
        // 1) order 조회
        Order order = orderRepository.findById(orderId)
                .filter(p->p.getDeletedAt() == null)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_ORDER));

        // 2) 주문 중복 검증
        if(paymentRepository.existsByOrderId(order.getId())){
            throw new ApplicationException(ErrorCode.PAYMENT_ALREADY_EXISTS);
        }

        Payment payment = PaymentCallbackRequest.toEntity(callbackRequest, order, user);
        return PaymentCreateResponse.fromEntity(paymentRepository.save(payment));
    }

}
