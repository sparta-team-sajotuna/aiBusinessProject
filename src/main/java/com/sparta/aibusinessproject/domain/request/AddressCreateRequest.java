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
public class AddressCreateRequest {

    @NotBlank
    private String state;
    @NotBlank
    private String city;
    @NotBlank
    private String town;
    @NotBlank
    private String detailAddress;

    public static Address toEntity(AddressCreateRequest request) {
        return Address.builder()
                .state(request.getState())
                .city(request.getCity())
                .town(request.getTown())
                .detailAddress(request.getDetailAddress())
                .build();
    }
}
