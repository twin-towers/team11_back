package com.example.hackathon11.converter;

import com.example.hackathon11.dto.ProjectItemResponseDto;
import com.example.hackathon11.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    public ProjectItemResponseDto entityToDto(Project project) {
        ProjectItemResponseDto result = ProjectItemResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .updateAt(project.getUpdateAt())
                .build();
        return result;
    }
}
