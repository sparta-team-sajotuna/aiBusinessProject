package com.sparta.aibusinessproject.ai;

import com.sparta.aibusinessproject.exception.Response;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {

    private final GeminiService service;

    @PostMapping
    public Response<AiMessageResponse> getMessage(@RequestBody AiMessgeReqeust reqeust, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return Response.success(new AiMessageResponse(service.getCompletion(reqeust.text(),userDetails.getUser())));
    }

//    @GetMapping
//    public Response<AiResponse> getData(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        return Response.success()
//    }
}
