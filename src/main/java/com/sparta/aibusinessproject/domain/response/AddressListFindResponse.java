package com.sparta.aibusinessproject.domain.response;

import com.sparta.aibusinessproject.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressListFindResponse {

    private UUID addressId;
    private String state;
    private String city;
    private String town;
    private String detailAddress;

    public static AddressListFindResponse from(Address address) {
        return AddressListFindResponse.builder()
                .addressId(address.getAddressId())
                .state(address.getState())
                .city(address.getCity())
                .town(address.getTown())
                .detailAddress(address.getDetailAddress())
                .build();
    }
}
