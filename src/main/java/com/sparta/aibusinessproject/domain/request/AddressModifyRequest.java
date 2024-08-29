package com.sparta.aibusinessproject.domain.request;

import com.sparta.aibusinessproject.domain.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressModifyRequest {

    private String state;
    private String city;
    private String town;
    private String detailAddress;

    public static Address toEntity(AddressModifyRequest request) {
        return Address.builder()
                .state(request.getState())
                .city(request.getCity())
                .town(request.getTown())
                .detailAddress(request.getDetailAddress())
                .build();
    }
}
