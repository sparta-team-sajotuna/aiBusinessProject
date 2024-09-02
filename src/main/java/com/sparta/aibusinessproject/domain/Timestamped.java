package com.sparta.aibusinessproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// Auditing 기능 활성화(데이터 이벤트 발생 날짜 저장)
@Setter
@ToString
//  MappedSuperclass = JPA의 에니티 클래스가 상속받을 경우 자식 클래스에게 매핑 정보를 전달함
// @MappedSuperclass = 부모클래스를 데이터베이스 테이블로 매핑하지 않고 자식 클래스에게 매핑 정보만 제공할때 사용
//  EntityListeners = 엔티티를 데이터베이스에 적용하기 전후로 콜백을 요청할 수 있게하는 어노테이션
//  AuditingEntityListener = 엔티티의  Auditing 정보를 중비하는 JPA 엔티티 리스너 클래스
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    private LocalDateTime deletedAt;
    private String deletedBy;

    @PrePersist     // 해당 엔티티를 저장하기 이전에 실행
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate      // 해당 엔티티를 업데이트 하기 이전에 실행
    public void onPreUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
