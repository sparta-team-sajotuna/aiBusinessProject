package com.sparta.aibusinessproject.domain;

import com.sparta.aibusinessproject.ai.AiSearchListResponse;
import com.sparta.aibusinessproject.domain.response.StoreSearchListResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "p_ai")
@Builder
// Delete의 값이 null인 정보만 가져옴
@Where(clause = "deleted_at is NULL")
// Delete 쿼리문이 동작될때, 실제로는 Delete쿼리문이 가지않고 아래의 쿼리문이 동작함
@SQLDelete(sql = "UPDATE p_ai SET deleted_at = current_timestamp WHERE id = ?")

public class Ai extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 50, nullable = false)
    private String question;

    private String message;

    public AiSearchListResponse toResponseDto() {
        return new AiSearchListResponse(
                user.getName(),
                question,
                message
        );
    }
}
