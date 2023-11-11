package com.example.hackathon11.repository;

import com.example.hackathon11.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
}
