package com.sparta.aibusinessproject.repository;

import com.sparta.aibusinessproject.domain.Ai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface AiRepository extends JpaRepository<Ai, UUID> , AiRepositoryCustom{

    List<Ai> findByUserName(String userName);
    List<Ai> findByUserUserId(String userName);

}
