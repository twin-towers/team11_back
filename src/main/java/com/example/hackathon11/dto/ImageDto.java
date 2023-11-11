package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
    private CoordinatesDto coordinates;
    private int rotate;
    private int zIndex;
    private String fit;
    private int height;
    private int width;
    private String src;
}
