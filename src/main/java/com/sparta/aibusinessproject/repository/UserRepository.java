package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
