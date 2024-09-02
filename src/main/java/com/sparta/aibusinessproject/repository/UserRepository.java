package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User a SET a.deletedAt = CURRENT_TIMESTAMP, a.deletedBy = :deletedBy WHERE a.userId = :userId")
    void delete(@Param("userId") String userId, @Param("deletedBy") String deletedBy);
}
