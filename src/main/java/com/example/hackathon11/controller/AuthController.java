package com.example.hackathon11.controller;

import com.example.hackathon11.config.CustomUserDetailsService;
import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.dto.JwtRequest;
import com.example.hackathon11.dto.JwtResponse;
import com.example.hackathon11.dto.UserDto;
import com.example.hackathon11.entity.User;
import com.example.hackathon11.exceptions.CustomBadCredentialsException;
import com.example.hackathon11.exceptions.InputDataErrorException;
import com.example.hackathon11.repository.UserRepository;
import com.example.hackathon11.services.UserService;
import com.example.hackathon11.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

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
