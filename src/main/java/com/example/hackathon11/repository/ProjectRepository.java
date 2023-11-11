package com.example.hackathon11.repository;

import com.example.hackathon11.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p where p.user.id = :id order by p.updateAt")
    List<Project> finByUserId(Long id);
}