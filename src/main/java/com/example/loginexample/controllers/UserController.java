package com.example.loginexample.controllers;


import com.example.loginexample.jwt.JwtService;
import com.example.loginexample.models.User;
import com.example.loginexample.models.dtos.UserDTO;
import com.example.loginexample.respository.UserRepository;
import com.example.loginexample.services.impl.AuthServiceImpl;
import com.example.loginexample.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/getUserByToken")
    public ResponseEntity<UserDTO> getUserByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return userService.getUserByToken(token);
    }




}
