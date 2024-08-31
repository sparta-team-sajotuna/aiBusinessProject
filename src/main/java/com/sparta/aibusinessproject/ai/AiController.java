package com.sparta.aibusinessproject.ai;

import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService service;


    @PostMapping
    public Response<AiMessageResponse> getMessage(@RequestBody AiMessgeReqeust reqeust, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(new AiMessageResponse(service.getCompletion(reqeust.text(),userDetails.getUser())));
    }


    // 유저별 상세 조회
    @GetMapping
    public Response<List<AiSearchResponse>> getData(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Response.success(service.getDataFromUser(userDetails));
    }

    // 데이터 전부 출력
    @PostMapping("/list")
    public Response<Page<AiSearchListResponse>> getAllData(@RequestBody AiSearchListRequest request, Pageable pageable) {
        return Response.success(service.getDataList(request,pageable));
    }
}
