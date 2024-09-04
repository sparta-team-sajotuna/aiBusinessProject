package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Store;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID>,StoreRepositoryCustom {
    Optional<Store> findByName(String storeName);

    @Modifying
    @Query("UPDATE Store s SET s.deletedAt = CURRENT TIMESTAMP , s.deletedBy = :deletedBy WHERE s.id = :storedId")
    void delete(@Param("storedId") UUID storedId, @Param("deletedBy")String deletedBy);

}
