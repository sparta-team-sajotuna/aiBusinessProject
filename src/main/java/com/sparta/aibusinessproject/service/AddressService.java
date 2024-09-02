package com.sparta.aibusinessproject.service;

import com.sparta.aibusinessproject.domain.Address;
import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.request.AddressCreateRequest;
import com.sparta.aibusinessproject.domain.request.AddressModifyRequest;
import com.sparta.aibusinessproject.domain.response.AddressListFindResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.AddressRepository;
import com.sparta.aibusinessproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j(topic = "Address Service")
@Service
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/address")
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    // 회원 주소 등록
    @Transactional
    public void createAddress(String userId, AddressCreateRequest addressCreateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApplicationException((ErrorCode.USER_NOT_FOUND)));
        // 현재 주소 개수 확인
        if (user.getAddresses().size() >= 5) {
            throw new ApplicationException(ErrorCode.ADDRESS_LIMIT_EXCEEDED); // 최대 주소 개수를 초과했을 때 예외 처리
        }
        user.addAddress(AddressCreateRequest.toEntity(addressCreateRequest));
        userRepository.save(user);
    }

    // 회원 주소 전체 조회
    public List<AddressListFindResponse> findAllAddressList(String userId) {
        List<AddressListFindResponse> responses = new ArrayList<>();
        List<Address> addressList = addressRepository.findAllByUser_UserId(userId);
        for (Address address : addressList) {
            responses.add(AddressListFindResponse.from(address));
        }
        return responses;
    }

    // 회원 주소 수정
    @Transactional
    public void modifyAddress(UUID userId, AddressModifyRequest addressId) {
        Address address = addressRepository.findById(userId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ADDRESS));
        address.update(addressId);
        addressRepository.save(address);
    }

    // 회원 주소 삭제
    @Transactional
    public void deleteAddress(UUID addressId, String userId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ADDRESS));
        addressRepository.delete(address.getAddressId(), userId);
    }

}
