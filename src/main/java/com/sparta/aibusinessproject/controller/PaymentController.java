package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.PaymentCallbackRequest;
import com.sparta.aibusinessproject.domain.request.PaymentCancelRequest;
import com.sparta.aibusinessproject.domain.response.PaymentCreateResponse;
import com.sparta.aibusinessproject.domain.response.PaymentFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentSearchRequest;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * 결제 단건 조회
     * @param paymentId
     * @return
     */
    @GetMapping("/{paymentId}")
    public Response<PaymentFindResponse> findPayment(@PathVariable UUID paymentId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(paymentService.findPayment(paymentId, userDetails.getUser()));
    }

    /**
     * 결제 전체 조회
     * @param searchDto
     * @param pageable
     * @param userDetails
     * @return
     */
    @GetMapping
    public Response<Page<PaymentFindResponse>> findOrders(PaymentSearchRequest searchDto,
                                                          @PageableDefault(size = 10) Pageable pageable,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails){

        return Response.success(paymentService.findAllPayments(searchDto, pageable, userDetails.getUser()));
    }

    /**
     * 결제 내역 저장 (결제 후 콜백)
     * @param callbackRequest
     * @param userDetails
     * @return
     */
    @PostMapping
    public Response<PaymentCreateResponse> createPayment(@RequestBody PaymentCallbackRequest callbackRequest,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(paymentService.createPayment(callbackRequest, userDetails.getUser()));
    }

    /**
     * 결제 내역 삭제
     * @param paymentId
     * @param userDetails
     * @return
     */
    @DeleteMapping("/{paymentId}")
    public Response<?> deletePayment(@PathVariable UUID paymentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        paymentService.deletePayment(paymentId, userDetails.getUser());
        return Response.success("해당 결제 정보가 삭제되었습니다.");
    }

    /**
     * 결제 취소
     * @param paymentId
     * @param userDetails
     * @return
     */
    @PatchMapping("/{paymentId}")
    public Response<?> cancelPayment(@RequestBody PaymentCancelRequest cancelRequest, @PathVariable UUID paymentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        paymentService.cancelPayment(cancelRequest.getOrderId(), paymentId, userDetails.getUser());
        return Response.success("해당 결제가 취소되었습니다.");
    }

}
