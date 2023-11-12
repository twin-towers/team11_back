package com.example.hackathon11.controller;

import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.dto.JwtResponse;
import com.example.hackathon11.dto.RegisterUserDto;
import com.example.hackathon11.dto.StringResponse;
import com.example.hackathon11.entity.User;
import com.example.hackathon11.exception.InputDataErrorException;
import com.example.hackathon11.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements CustomConstants {
    private final UserService userService;

    @CrossOrigin(origins = {"http://localhost:5173", "https://team11-front.vercel.app"})
    @PostMapping()
    public JwtResponse registerNewUser(@RequestBody RegisterUserDto registerUserDto)
            throws InputDataErrorException {

        Optional<User> user = userService.findByUsername(registerUserDto.getEmail());
        if (user.isPresent()) {
            throw new InputDataErrorException(EMAIL_ALREADY_EXISTS);
        }

        return userService.createNewUser(registerUserDto);
        // пользователя нет - регистрируем
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://team11-front.vercel.app"})
    @PutMapping()
    public StringResponse editUser(@RequestBody RegisterUserDto registerUserDto,
                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization)
            throws InputDataErrorException {

        String token = authorization.substring(7);
        return userService.editUser(registerUserDto, token);
    }

}
