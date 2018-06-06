package com.serendipity.repositories;

import com.serendipity.entities.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    @Query("FROM Memory m WHERE m.owner = :userId")
    List<Memory> findWByUserId(int userId);
}
