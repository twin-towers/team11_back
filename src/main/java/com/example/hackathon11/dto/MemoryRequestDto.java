package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryRequestDto {
    private String difficulty;
    private Integer moves;
    private Integer time;           //milliseconds
}
