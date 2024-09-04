package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByUser_UserId(String userId);

    @Modifying
    @Query("UPDATE Address a SET a.deletedAt = CURRENT_TIMESTAMP, a.deletedBy = :deletedBy WHERE a.addressId = :addressId")
    void delete(@Param("addressId") UUID addressId, @Param("deletedBy") String deletedBy);

}
