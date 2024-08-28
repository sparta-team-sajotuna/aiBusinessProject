package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existsByName(String categoryName);

    Optional<Category> findByName(String category);
}
