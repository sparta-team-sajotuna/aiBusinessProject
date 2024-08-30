package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByUser_UserId(String userId);
}
