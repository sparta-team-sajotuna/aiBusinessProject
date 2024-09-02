package com.sparta.aibusinessproject.domain;

import jakarta.persistence.*;
import lombok.Builder;
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

    @CreatedBy
    @Column(name = "created_by", updatable = false, nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    //@updateCreatedAt() ,updateModifiedAt() 은 MappedSuperclass를 생성하기 위해 이미 정의되어 있기  때문에  해당 클래스에서 애노테이션을 사용 해 구현 할 수 없다.
    // 애노테이션을 제외한 메소드만 작성후 사용하는 해당 클래스에 라이프사이클 메서드를 구현해준다.
    // @PrePersist
    public void updateCreated(User user) {
        this.createdAt = LocalDateTime.now();
        this.createdBy = user.getUserId();
    }

    //  @PreUpdate
    //  @PrePersist
    public void updateModified(User user) {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = user.getUserId();
    }

}
