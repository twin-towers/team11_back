package com.example.hackathon11.controller;

import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.dto.JwtResponse;
import com.example.hackathon11.dto.RegisterUserDto;
import com.example.hackathon11.entity.User;
import com.example.hackathon11.exceptions.InputDataErrorException;
import com.example.hackathon11.repository.UserRepository;
import com.example.hackathon11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements CustomConstants {
    private final UserRepository userRepository;
    private final UserService userService;

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


//    @GetMapping("count-users")
//    public String getCountUsers(){
//        long numberUsers = userRepository.count();
//        return "Number of users = "+numberUsers;
//    }
//
//    @GetMapping("add-user")
//    public String addUser(){
//        User user = new User();
//        user.setFirstName("Render");
//        user.setLastName("Spring Boot");
//        userRepository.save(user);
//        return "User added successfully";
//    }
}
