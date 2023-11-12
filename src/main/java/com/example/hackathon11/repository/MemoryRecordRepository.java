package com.example.hackathon11.repository;

import com.example.hackathon11.entity.MemoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRecordRepository extends JpaRepository<MemoryRecord, Long> {

    @Query("select mr from MemoryRecord mr order by mr.id desc")
    List<MemoryRecord> findAll();

    @Query("select mr from MemoryRecord mr where mr.user.id = :id order by mr.id desc")
    List<MemoryRecord> findByUserId(Long id);
}
