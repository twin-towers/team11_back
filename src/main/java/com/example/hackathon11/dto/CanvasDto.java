package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanvasDto {
    private String backgroundColor;
    private String backgroundImage;
    private int height;
    private int width;
    private int padding;
}
