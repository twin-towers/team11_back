package com.example.hackathon11.converter;

import com.example.hackathon11.dto.MemoryRecordDto;
import com.example.hackathon11.entity.MemoryRecord;
import org.springframework.stereotype.Component;

@Component
public class MemoryRecordConverter {
    public MemoryRecordDto entityToDto(MemoryRecord memoryRecord) {
        MemoryRecordDto result = MemoryRecordDto.builder()
                .id(memoryRecord.getId())
                .userId(memoryRecord.getUser().getId())
                .moves(memoryRecord.getMoves())
                .time(memoryRecord.getTime())
                .difficulty(
                        switch (memoryRecord.getDifficulty()) {
                            case HARD -> "Hard";
                            case MEDIUM -> "Medium";
                            case VERY_HARD -> "VeryHard";
                            default -> "Easy";
                })
                .build();
        return result;
    }
}
