package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Menu;
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
public class PaymentCreateResponse {
    private PaymentStatusEnum status;
    private int payAmount;
    private String payMethod;

    public static PaymentCreateResponse fromEntity(Payment payment) {
        return PaymentCreateResponse.builder()
                .status(payment.getStatus())
                .payAmount(payment.getPayAmount())
                .payMethod(payment.getPayMethod())
                .build();
    }
}
