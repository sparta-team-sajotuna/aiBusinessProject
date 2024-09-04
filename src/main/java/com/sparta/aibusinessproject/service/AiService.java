package com.sparta.aibusinessproject.service;


import com.sparta.aibusinessproject.ai.*;
import com.sparta.aibusinessproject.domain.Ai;
import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.dto.AiDto;
import com.sparta.aibusinessproject.domain.request.AiSearchListRequest;
import com.sparta.aibusinessproject.domain.response.AiSearchListResponse;
import com.sparta.aibusinessproject.domain.response.AiSearchResponse;
import com.sparta.aibusinessproject.exception.ApplicationException;
import com.sparta.aibusinessproject.exception.ErrorCode;
import com.sparta.aibusinessproject.repository.AiRepository;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AiService {
    public static final String GEMINI_PRO = "gemini-pro";

    private final GeminiInterface geminiInterface;
    private final AiRepository aiRepository;


    public GeminiResponse getCompletion(GeminiRequest request){
        return geminiInterface.getCompletion(GEMINI_PRO, request);
    }

    public String getAiResponse(String message){
        GeminiRequest geminiRequest = new GeminiRequest(message+", 답변을 최대한 간결하게 50자 이하로 해줘");
        GeminiResponse response = getCompletion(geminiRequest);

        String aiResult = response.getCandidates()
                .stream()
                .findFirst().flatMap(candidate -> candidate.getContent().getParts()
                        .stream()
                        .findFirst()
                        .map(GeminiResponse.TextPart::getText))
                .orElse(null);
        return aiResult;
    }

    @Transactional
    public String getCompletion(String text, User user){
        String aiResponseMessage = getAiResponse(text);

        // DB 저장
        aiRepository.save(AiDto.AiDto(user,text,aiResponseMessage));
        return aiResponseMessage;
    }

    public List<AiSearchResponse> getDataFromUser(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        List<AiSearchResponse> aiList = aiRepository.findByUserUserId(user.getUserId()).stream()
                .map(s->AiSearchResponse.from(s))
                .toList();

        return aiList;
    }

    // 가게 리스트 출력
    public Page<AiSearchListResponse> getDataList(Pageable pageable) {

        return aiRepository.searchAi(pageable);
    }

    @Transactional
    public UUID delete(UUID aiId, User user) {

        Ai ai = aiRepository.findById(aiId)
                .orElseThrow(()-> new ApplicationException(ErrorCode.INVALID_AI));

        if(!ai.getUser().getUserId().equals(user.getUserId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        aiRepository.delete(ai.getId(),user.getUserId());
        return aiId;
    }


}
