package com.example.hackathon11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

}
