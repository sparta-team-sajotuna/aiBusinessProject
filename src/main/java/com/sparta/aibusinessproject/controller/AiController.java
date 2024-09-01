package com.sparta.aibusinessproject.controller;

import com.sparta.aibusinessproject.domain.request.AiMessgeReqeust;
import com.sparta.aibusinessproject.domain.request.AiSearchListRequest;
import com.sparta.aibusinessproject.domain.response.AiMessageResponse;
import com.sparta.aibusinessproject.domain.response.AiSearchListResponse;
import com.sparta.aibusinessproject.domain.response.AiSearchResponse;
import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import com.sparta.aibusinessproject.service.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Tag(name = "AI API", description = "Ai와 연동하여 질문에 해당하는 답변을 자동으로 생성합니다")
public class AiController {

    private final AiService service;


    @PostMapping
    @Operation(summary = "답변 자동 생성", description = "AI Api 연동, 답변 자동 생성")
    public Response<AiMessageResponse> getMessage(@RequestBody AiMessgeReqeust reqeust, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(new AiMessageResponse(service.getCompletion(reqeust.text(),userDetails.getUser())));
    }


    // 유저별 상세 조회
    @GetMapping
    @Operation(summary = "AI 상세 조회", description = "User별 AI 질문, 답변 상세 조회")
    public Response<List<AiSearchResponse>> getData(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(service.getDataFromUser(userDetails));
    }

    // 데이터 전부 출력
    @PostMapping("/list")
    @Operation(summary = "AI 리스트 조회", description = "AI 질문, 답변 리스트 전체 조회")
    public Response<Page<AiSearchListResponse>> getAllData(@RequestBody AiSearchListRequest request, Pageable pageable) {
        return Response.success(service.getDataList(request,pageable));
    }

    // 가게 삭제
    @DeleteMapping("/{aiId}")
    @Operation(summary = "AI 데이터 삭제", description = "AI 질문, 답변 데이터 삭제")
    public Response<?> storeDelete(@PathVariable UUID aiId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UUID  uuid = service.delete(aiId,userDetails.getUser());
        return Response.success( uuid +"가게 정보가 삭제되었습니다.");
    }

}
