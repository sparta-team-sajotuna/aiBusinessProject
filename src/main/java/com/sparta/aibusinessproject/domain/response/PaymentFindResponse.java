package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Payment;
import com.sparta.aibusinessproject.domain.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFindResponse {
    private UUID paymentId;
    private PaymentStatusEnum status;
    private int payAmount;
    private String paymentMethod;

    public static PaymentFindResponse fromEntity(Payment payment) {
        return PaymentFindResponse.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .payAmount(payment.getPayAmount())
                .paymentMethod(payment.getPayMethod())
                .build();
    }
}
