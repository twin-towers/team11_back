package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryRequestDto {
    private String difficulty;
    private Integer moves;
    private Integer time;           //milliseconds
}
