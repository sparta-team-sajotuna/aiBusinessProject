package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.AddressCreateRequest;
import com.sparta.aibusinessproject.domain.request.AddressModifyRequest;
import com.sparta.aibusinessproject.domain.response.AddressListFindResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j(topic = "Address Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/address")
public class AddressController {

    private final AddressService addressService;

    // 회원 주소 등록
    @PostMapping
    public Response<String> createAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid AddressCreateRequest addressCreateRequest) {
        addressService.createAddress(userDetails.getUsername(), addressCreateRequest);
        return Response.success("주소 등록이 완료되었습니다.");
    }

    // 회원 주소 전체 조회
    @GetMapping
    public Response<List<AddressListFindResponse>> findAllAddressList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(addressService.findAllAddressList(userDetails.getUsername()));
    }

    // 회원 주소 업데이트
    @PatchMapping("/{addressId}")
    public Response<String> modifyAddress(@PathVariable UUID addressId, @RequestBody AddressModifyRequest addressModifyRequest) {
        addressService.modifyAddress(addressId, addressModifyRequest);
        return Response.success("주소 수정이 완료되었습니다.");
    }

    // 회원 주소 삭제
    @DeleteMapping("/{addressId}")
    public Response<String> deleteAddress(@PathVariable UUID addressId) {
        addressService.deleteAddress(addressId);
        return Response.success("주소가 삭제가 완료되었습니다.");
    }
}
