package com.sparta.aibusinessproject.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSearchRequest {
    private Double minPrice;
    private Double maxPrice;
}
