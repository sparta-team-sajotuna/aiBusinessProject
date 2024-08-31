package com.sparta.aibusinessproject.ai;


import com.sparta.aibusinessproject.domain.User;
import com.sparta.aibusinessproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class AiService {
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

    public List<AiSearchResponse> getDataFromUser(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        List<AiSearchResponse> aiList = aiRepository.findByUserUserId(user.getUserId()).stream()
                .map(s->AiSearchResponse.from(s))
                .toList();

        return aiList;
    }

    // 가게 리스트 출력
    public Page<AiSearchListResponse> getDataList(AiSearchListRequest request, Pageable pageable) {
        int pageableSize = 0;
        List<Integer> sizeList = List.of(10,30,50);

        // TODO : 사이즈 변경
        if(sizeList.stream().anyMatch(s -> request.pageSize() != s)){
            pageableSize = 10;
        }else{
            pageableSize = request.pageSize();
        }

        System.out.println(pageableSize);

        return aiRepository.searchAi(pageableSize, pageable);
    }
}
