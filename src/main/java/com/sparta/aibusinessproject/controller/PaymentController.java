package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.PaymentCallbackRequest;
import com.sparta.aibusinessproject.domain.request.PaymentCancelRequest;
import com.sparta.aibusinessproject.domain.response.PaymentCreateResponse;
import com.sparta.aibusinessproject.domain.response.PaymentFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentSearchRequest;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
@Tag(name = "Payment API", description = "결제 CRUD")
public class PaymentController {

    private static final int[] ALLOWED_PAGE_SIZES = {10, 30, 50};
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final PaymentService paymentService;

    /**
     * 결제 단건 조회
     * @param paymentId
     * @return
     */
    @GetMapping("/{paymentId}")
    @Operation(summary = "결제 단건 조회", description = "유저에 대한 결제 상세 조회", security = @SecurityRequirement(name = "bearerAuth"))
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
    @Operation(summary = "결제 전체 조회", description = "유저에 대한 결제 전체 리스트 조회", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<Page<PaymentFindResponse>> findOrders(PaymentSearchRequest searchDto,
                                                          Pageable pageable,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails){

        int size = DEFAULT_PAGE_SIZE; // 기본 10건
        if(Arrays.stream(ALLOWED_PAGE_SIZES).anyMatch(s->s == pageable.getPageSize())){ //요청 사이즈가 10, 30, 50일 때
            size = pageable.getPageSize();
        }

        Pageable validatedPageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());

        return Response.success(paymentService.findAllPayments(searchDto, validatedPageable, userDetails.getUser()));
    }

    /**
     * 결제 내역 저장 (결제 후 콜백)
     * @param callbackRequest
     * @param userDetails
     * @return
     */
    @PostMapping
    @Operation(summary = "결제 내역 저장", description = "결제 후 해당 결제 내역 저장", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<PaymentCreateResponse> createPayment(@RequestBody @Valid PaymentCallbackRequest callbackRequest,
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
    @Operation(summary = "결제 내역 삭제", description = "유저에 대한 결제 내역 삭제", security = @SecurityRequirement(name = "bearerAuth"))
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
    @Operation(summary = "결제 취소", description = "결제 후 해당 결제에 대해 취소", security = @SecurityRequirement(name = "bearerAuth"))
    public Response<?> cancelPayment(@RequestBody @Valid PaymentCancelRequest cancelRequest, @PathVariable UUID paymentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        paymentService.cancelPayment(cancelRequest.getOrderId(), paymentId, userDetails.getUser());
        return Response.success("해당 결제가 취소되었습니다.");
    }

}
