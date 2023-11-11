package com.example.hackathon11.converter;

import com.example.hackathon11.dto.UserDto;
import com.example.hackathon11.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto entityToDto(User user) {
        UserDto result = UserDto.builder()
                .id(user.getId())
                .username(user.getNickname())       // NICKNAME -> username
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getUsername())          // USERNAME -> email
                .build();
        return result;
    }
}
