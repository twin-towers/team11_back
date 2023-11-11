package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private CanvasDto canvas;
    private List<SimpleIconDto> simpleIcons;
    private List<ImageIconDto> imageIcons;
    private List<ImageDto> images;
    private List<TextDto> texts;
    private String name;
}


