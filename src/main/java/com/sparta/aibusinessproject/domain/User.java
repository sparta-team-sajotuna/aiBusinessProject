package com.sparta.aibusinessproject.domain;

import com.sparta.aibusinessproject.domain.request.UserModifyRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_users")
// Delete의 값이 null인 정보만 가져옴
@Where(clause = "deleted_at is NULL")
// Delete 쿼리문이 동작될때, 실제로는 Delete쿼리문이 가지않고 아래의 쿼리문이 동작함
@SQLDelete(sql = "UPDATE p_users SET deleted_at = current_timestamp WHERE user_id = ?")
public class User extends Timestamped {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    // 회원 정보 수정 시 사용되는 메서드
    public void update(UserModifyRequest request, String modifiedPassword) {
        this.password = modifiedPassword != null ? modifiedPassword : this.password;
        this.name = request.getName() != null ? request.getName() : this.name;
        this.phone = request.getPhone() != null ? request.getPhone() : this.phone;
        this.email = request.getEmail() != null ? request.getEmail() : this.email;
    }

    // 회원 주소 추가 시 사용되는 메서드
    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    @PrePersist
    public void prePersist() {
        super.updateCreated(this);
        super.updateModified(this);
    }

    @PreUpdate
    public void preUpdate() {
        super.updateModified(this);
    }

}
