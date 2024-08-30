package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.Payment;
import com.sparta.aibusinessproject.domain.PaymentStatusEnum;
import com.sparta.aibusinessproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCallbackRequest {
    private UUID orderId;
    // 포트원 - pg사들을 모아서 한번에 결제서비스 제공해주는 솔루션
    //포트원 결제 callback 응답 정보 https://portone.gitbook.io/docs/sdk/javascript-sdk/payrt
    //포트원 주문번호 -> 결제창 요청 시 항상 고유 값으로 채번 되어야 함.
    private String merchantUid; // 포트원 요구사항 : 주문번호는 가맹점 서버에서 고유하게 채번하여 DB 상에 저장해주세요. https://portone.gitbook.io/docs/auth/guide/3.
    //처음 요청한 금액은 merchant_uid로 데이터베이스에서 조회하고
    //실제 결제 금액은 imp_uid로 포트원 서버에서 조회하여 값 비교
    //검증 성공 시 db에 결제 정보 저장
    private String impUid; // 포트원 거래 고유번호 -> 포트원으로 결제내역 단건 조회 및 결제 취소 시 사용됨
    private String payMethod; // 결제 수단
    private int paidAmount; // 결제 금액
    private PaymentStatusEnum status; // ready: 미결제, paid: 결제 완료, failed: 잔액부족 등

    public static Payment toEntity(PaymentCallbackRequest requestDto, Order order, User user) {
        return Payment.builder()
                .payAmount(requestDto.getPaidAmount())
                .status(requestDto.getStatus())
                .merchantUid(requestDto.getMerchantUid())
                .impUid(requestDto.getImpUid())
                .payMethod(requestDto.payMethod)
                .order(order)
                .user(user)
                .build();
    }
}
