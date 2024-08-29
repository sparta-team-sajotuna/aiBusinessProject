package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID>, MenuRepositoryCustom {
}
