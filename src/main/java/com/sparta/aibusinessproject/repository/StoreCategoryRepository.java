package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE StoreCategory a SET a.deletedAt = current_timestamp WHERE a.category.id = :categoryId")
    void deleteByCategoryId(UUID categoryId);
}
