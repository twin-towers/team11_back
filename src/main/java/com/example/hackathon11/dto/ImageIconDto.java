package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageIconDto {
    private CoordinatesDto coordinates;
    private int rotate;
    private int zIndex;
    private String id;
    private int size;
}

