package com.example.hackathon11.controller;

import com.example.hackathon11.dto.MemoryRecordDto;
import com.example.hackathon11.dto.MemoryRequestDto;
import com.example.hackathon11.dto.StringResponse;
import com.example.hackathon11.exception.InputDataErrorException;
import com.example.hackathon11.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/memory")
public class MemoryController {
    private final MemoryService memoryService;

    @CrossOrigin(origins = {"http://localhost:5173", "https://team11-front.vercel.app"})
    @GetMapping()
    public List<MemoryRecordDto> getMemoryRecords()  {

        return memoryService.getMemoryRecords();
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://team11-front.vercel.app"})
    @PostMapping()
    public StringResponse addMemoryEntry(@RequestBody MemoryRequestDto memoryRequestDto,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION)
                                         String authorization) throws InputDataErrorException {

        String token = authorization.substring(7);
        return memoryService.addMemoryEntry(memoryRequestDto, token);
    }


    @CrossOrigin(origins = {"http://localhost:5173", "https://team11-front.vercel.app"})
    @GetMapping("user")
    public List<MemoryRecordDto> getUserMemoryRecords(@RequestParam Long id)  {

        return memoryService.getUserMemoryRecords(id);
    }

}
