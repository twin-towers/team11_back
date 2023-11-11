package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectItemResponseDto {
    private Long id;
    private String name;
    private LocalDateTime updateAt;
}
