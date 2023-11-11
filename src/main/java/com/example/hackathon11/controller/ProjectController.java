package com.example.hackathon11.controller;

import com.example.hackathon11.dto.ProjectDto;
import com.example.hackathon11.dto.ProjectItemResponseDto;
import com.example.hackathon11.dto.StringResponse;
import com.example.hackathon11.exceptions.InputDataErrorException;
import com.example.hackathon11.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping()
    public StringResponse saveProject(@RequestBody ProjectDto projectDto,
                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization ) throws InputDataErrorException {

        String token = authorization.substring(7);
        return projectService.save(projectDto, token);

    }

    @GetMapping()
    public List<ProjectItemResponseDto> getProjects (@RequestHeader(HttpHeaders.AUTHORIZATION)
                                                   String authorization )
            throws InputDataErrorException {

        String token = authorization.substring(7);
        return projectService.getProjects(token);

    }
}
