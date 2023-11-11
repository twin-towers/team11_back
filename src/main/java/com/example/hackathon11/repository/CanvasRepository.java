package com.example.hackathon11.repository;

import com.example.hackathon11.entity.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanvasRepository extends JpaRepository<Canvas, Long> {
}
