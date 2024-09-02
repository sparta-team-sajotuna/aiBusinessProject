package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> , CategoryRepositoryCustom{
    boolean existsByName(String categoryName);

    Optional<Category> findByName(String category);

    @Modifying
    @Query("UPDATE Category c SET c.deletedAt = CURRENT TIMESTAMP , c.deletedBy = :deletedBy WHERE c.id = :categoryId")
    void delete(@Param("categoryId") UUID categoryId, @Param("deletedBy")String deletedBy);
}
