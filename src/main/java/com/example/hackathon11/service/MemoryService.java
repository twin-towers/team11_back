package com.example.hackathon11.service;

import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.converter.MemoryRecordConverter;
import com.example.hackathon11.dto.MemoryRecordDto;
import com.example.hackathon11.dto.MemoryRequestDto;
import com.example.hackathon11.dto.StringResponse;
import com.example.hackathon11.entity.MemoryDifficulty;
import com.example.hackathon11.entity.MemoryRecord;
import com.example.hackathon11.entity.User;
import com.example.hackathon11.exception.InputDataErrorException;
import com.example.hackathon11.repository.MemoryRecordRepository;
import com.example.hackathon11.repository.UserRepository;
import com.example.hackathon11.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoryService implements CustomConstants {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final MemoryRecordConverter memoryRecordConverter;
    private final MemoryRecordRepository memoryRecordRepository;

    public List<MemoryRecordDto> getMemoryRecords() {
        return memoryRecordRepository.findAll().stream().map(memoryRecordConverter::entityToDto).toList();
    }

    public StringResponse addMemoryEntry(MemoryRequestDto memoryRequestDto, String token) throws InputDataErrorException {
            String email = jwtTokenUtil.getUsernameFromToken(token);

            User user = userRepository.findByUsername(email).orElseThrow
                    (() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));

            MemoryRecord memoryRecord = new MemoryRecord();
            if (memoryRequestDto.getDifficulty()==null || memoryRequestDto.getDifficulty().isBlank()) {
                memoryRecord.setDifficulty(MemoryDifficulty.EASY);
            } else {
                memoryRecord.setDifficulty(
                        switch (memoryRequestDto.getDifficulty()) {
                            case "Medium" -> MemoryDifficulty.MEDIUM;
                            case "Hard" -> MemoryDifficulty.HARD;
                            case "VeryHard" -> MemoryDifficulty.VERY_HARD;
                            default -> MemoryDifficulty.EASY;
                        });
            }
            if (memoryRequestDto.getMoves()==null) {
                throw new InputDataErrorException(MOVES_ABSENT);
            }
            if (memoryRequestDto.getTime()==null) {
                throw new InputDataErrorException(TIME_ABSENT);
            }
            memoryRecord.setMoves(memoryRequestDto.getMoves());
            memoryRecord.setTime(memoryRequestDto.getTime());
            memoryRecord.setUser(user);
            memoryRecordRepository.save(memoryRecord);
            return new StringResponse(RECORD_ADDED_SUCCESSFULLY);

    }

    public List<MemoryRecordDto> getUserMemoryRecords(Long id) {
        return memoryRecordRepository.findByUserId(id).stream().map(memoryRecordConverter::entityToDto)
                .toList();
    }
}
