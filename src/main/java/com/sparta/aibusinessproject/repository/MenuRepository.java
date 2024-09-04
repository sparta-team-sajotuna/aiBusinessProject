package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID>, MenuRepositoryCustom {
    @Modifying
    @Query("UPDATE Menu a SET a.deletedAt = CURRENT_TIMESTAMP, a.deletedBy = :deletedBy WHERE a.id = :menuId")
    void delete(@Param("menuId") UUID menuId, @Param("deletedBy") String deletedBy);
}
