package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoryRecordDto {
    private Long id;
    private String difficulty;
    private Integer moves;
    private Integer time;           //milliseconds
    private Long userId;
}
