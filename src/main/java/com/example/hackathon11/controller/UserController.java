package com.example.hackathon11.controller;

import com.example.hackathon11.entity.User;
import com.example.hackathon11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;


    @GetMapping("count-users")
    public String getCountUsers(){
        long numberUsers = userRepository.count();
        return "Number of users = "+numberUsers;
    }

    @GetMapping("add-user")
    public String addUser(){
        User user = new User();
        user.setFirstName("Render");
        user.setLastName("Spring Boot");
        userRepository.save(user);
        return "User added successfully";
    }
}
