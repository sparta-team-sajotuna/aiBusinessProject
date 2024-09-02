package com.sparta.aibusinessproject.ai;

import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.domain.dto.AiDto;
import com.sparta.aibusinessproject.repository.AiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {
    public static final String GEMINI_PRO = "gemini-pro";

    private final GeminiInterface geminiInterface;
    private final AiRepository aiRepository;

    private GeminiResponse getCompletion(GeminiRequest request){
        return geminiInterface.getCompletion(GEMINI_PRO, request);
    }

    public String getCompletion(String text, User user){
        GeminiRequest geminiRequest = new GeminiRequest(text+", 답변을 최대한 간결하게 50자 이하로 해줘");
        GeminiResponse response = getCompletion(geminiRequest);

        String aiResult = response.getCandidates()
                .stream()
                .findFirst().flatMap(candidate -> candidate.getContent().getParts()
                        .stream()
                        .findFirst()
                        .map(GeminiResponse.TextPart::getText))
                .orElse(null);
        
        // DB 저장
        aiRepository.save(AiDto.AiDto(user,text,aiResult));
        return aiResult;
    }
}
