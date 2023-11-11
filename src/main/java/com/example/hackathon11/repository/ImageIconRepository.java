package com.example.hackathon11.repository;


import com.example.hackathon11.entity.ImageIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageIconRepository extends JpaRepository<ImageIcon, String> {
}
