package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
