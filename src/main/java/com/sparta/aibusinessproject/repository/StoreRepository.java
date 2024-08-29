package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID>,StoreRepositoryCustom {
    Optional<Store> findByName(String storeName);

}
