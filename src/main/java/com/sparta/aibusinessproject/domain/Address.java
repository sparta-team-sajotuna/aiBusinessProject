package com.sparta.aibusinessproject.domain;

import com.sparta.aibusinessproject.domain.request.AddressModifyRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_address")
// Delete의 값이 null인 정보만 가져옴
@Where(clause = "deleted_at is NULL")
// Delete 쿼리문이 동작될때, 실제로는 Delete쿼리문이 가지않고 아래의 쿼리문이 동작함
//@SQLDelete(sql = "UPDATE p_address SET deleted_at = current_timestamp WHERE address_id = ?")
//@SQLDelete(sql = "UPDATE p_address SET deleted_at = current_timestamp, deleted_by = ? WHERE address_id = ?")
public class Address extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id", nullable = false)
    private UUID addressId;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "town", nullable = false)
    private String town;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    // 회원 주소 수정 시 사용되는 메서드
    public void update(AddressModifyRequest request) {
        this.state = request.getState() != null ? request.getState() : this.state;
        this.city = request.getCity() != null ? request.getCity() : this.city;
        this.town = request.getTown() != null ? request.getTown() : this.town;
        this.detailAddress = request.getDetailAddress() != null ? request.getDetailAddress() : this.detailAddress;
    }

//    @PrePersist
//    public void prePersist() {
//        super.updateCreated(this.user);
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        super.updateModified(this.user);
//    }

    // 논리 삭제가 아닌 물리 삭제 시 호출 됨
//    @PreRemove
//    public void preRemove() {
//        super.updateDeleted(this.user);
//    }
}
