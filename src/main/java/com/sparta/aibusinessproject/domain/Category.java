package com.sparta.aibusinessproject.domain;

import com.sparta.aibusinessproject.domain.request.CategoryCreateRequest;
import com.sparta.aibusinessproject.domain.request.StoreUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "p_category")
@Builder
// Delete의 값이 null인 정보만 가져옴
@Where(clause = "deleted_at is NULL")
// Delete 쿼리문이 동작될때, 실제로는 Delete쿼리문이 가지않고 아래의 쿼리문이 동작함
@SQLDelete(sql = "UPDATE p_category SET deleted_at = current_timestamp WHERE id = ?")
public class Category extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;        // 카테고리 이름


    public void update(CategoryCreateRequest request) {
        this.name = request.name() != null ? request.name() : this.name;
    }
}
