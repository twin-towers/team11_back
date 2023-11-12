package com.example.hackathon11.controller;

import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.dto.JwtRequest;
import com.example.hackathon11.dto.JwtResponse;
import com.example.hackathon11.dto.UserDto;
import com.example.hackathon11.exceptions.InputDataErrorException;
import com.example.hackathon11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements CustomConstants {
    private final UserService userService;


    @CrossOrigin("http://localhost:5173")
    @PostMapping()
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return userService.createAuthToken(authRequest);
    }

    @CrossOrigin("http://localhost:5173")
    @GetMapping()
    public UserDto getUserDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization)
            throws InputDataErrorException {
        String token = authorization.substring(7);
        return userService.getUserDetails(token);
    }


}
