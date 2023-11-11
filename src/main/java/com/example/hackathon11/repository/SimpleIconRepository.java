package com.example.hackathon11.repository;

import com.example.hackathon11.entity.SimpleIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SimpleIconRepository extends JpaRepository<SimpleIcon, String> {
}
