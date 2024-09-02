package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface RefreshRepository extends JpaRepository<RefreshToken, UUID> {

    Boolean existsByRefresh(String refresh);
    void deleteByRefresh(String refresh);
}
