package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDto {
    private CoordinatesDto coordinates;
    private int rotate;
    private int zIndex;
    private String color;
    private String decoration;
    private String hRef;
    private boolean isItalic;
    private String name;
    private int size;
    private String text;
    private int weight;
}
