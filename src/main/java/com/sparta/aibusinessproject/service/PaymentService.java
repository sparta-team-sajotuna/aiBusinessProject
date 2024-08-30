package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.*;
import com.sparta.aibusinessproject.domain.request.PaymentCallbackRequest;
import com.sparta.aibusinessproject.domain.response.PaymentCreateResponse;
import com.sparta.aibusinessproject.domain.response.PaymentFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentSearchRequest;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.OrderRepository;
import com.sparta.aibusinessproject.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional(readOnly = true)
    public PaymentFindResponse findPayment(UUID paymentId, User user) {
        // 롤체크 - 결제  내역은 고객과 관리자만
        if(!user.getRole().equals(UserRoleEnum.CUSTOMER) && !user.getRole().equals(UserRoleEnum.MANAGER)){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        // 1) payment 조회
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_PAYMENT));

        // 2) 회원 검증 - 고객 : 본인 결제인지 확인, 관리자 모든 결제 내역 확인
        if(user.getRole().equals(UserRoleEnum.CUSTOMER)){
            if(!payment.getUser().getUserId().equals(user.getUserId())){
                throw new ApplicationException(ErrorCode.NOT_YOUR_PAYMENT);
            }
        }

        return PaymentFindResponse.fromEntity(payment);

    }

    @Transactional(readOnly = true)
    public Page<PaymentFindResponse> findAllPayments(PaymentSearchRequest searchDto, Pageable pageable, User user) {
        // 롤체크 - 결제  내역은 고객과 관리자만
        if(!user.getRole().equals(UserRoleEnum.CUSTOMER) && !user.getRole().equals(UserRoleEnum.MANAGER)){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }
        return paymentRepository.searchPayments(searchDto, pageable, user.getRole(), user.getUserId());
    }

    @Transactional
    public PaymentCreateResponse createPayment(PaymentCallbackRequest callbackRequest, User user) {
        if(!user.getRole().equals(UserRoleEnum.CUSTOMER)){ //결제는 고객만
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }
        UUID orderId = callbackRequest.getOrderId();
        // 1) order 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_ORDER));

        // 2) 주문 중복 검증
        if(paymentRepository.existsByOrderId(order.getId())){
            throw new ApplicationException(ErrorCode.PAYMENT_ALREADY_EXISTS);
        }

        // 3) 실제 결제된 금액이랑 주문한 금액이랑 같은지 검증
        if(callbackRequest.getPaidAmount() != order.getTotalPrice()){
            throw new ApplicationException(ErrorCode.WRONG_AMOUNT);
        }

        // 4) 본인 주문만 결제 가능
        if(!order.getUser().getUserId().equals(user.getUserId())
                || !order.getStatus().equals(OrderStatusEnum.CREATED)){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        // 주문테이블에 결제수단 저장
        order.updatePaymentMethod(callbackRequest.getPayMethod());
        orderRepository.save(order);

        Payment payment = PaymentCallbackRequest.toEntity(callbackRequest, order, user);
        return PaymentCreateResponse.fromEntity(paymentRepository.save(payment));
    }

    public void deletePayment(UUID paymentId, User user) {
        // 롤체크 - 결제 삭제는 고객만
        if(!user.getRole().equals(UserRoleEnum.CUSTOMER)){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        // 1) payment 조회
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_PAYMENT));

        // 본인 결제만 삭제 가능
        if(!payment.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        paymentRepository.delete(payment);
    }

    public void cancelPayment(UUID orderId, UUID paymentId, User user) {
        // 롤체크 - 결제 취소는 고객만
        if(!user.getRole().equals(UserRoleEnum.CUSTOMER)){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        // 1) payment 조회
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_PAYMENT));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_ORDER));

        // 본인 결제만 취소 가능
        if(!payment.getUser().getUserId().equals(user.getUserId()) || order.getStatus().equals(OrderStatusEnum.CANCELLED)){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        payment.cancelPayment();
        paymentRepository.save(payment);
    }
}
