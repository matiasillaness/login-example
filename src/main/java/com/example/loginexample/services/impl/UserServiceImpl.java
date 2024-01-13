package com.example.loginexample.services.impl;

import com.example.loginexample.jwt.JwtService;
import com.example.loginexample.models.User;
import com.example.loginexample.models.dtos.UserDTO;
import com.example.loginexample.respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ResponseEntity<UserDTO> getUserByToken(String token) {
        String username = jwtService.getUsernameFromToken(token);

        User user = userRepository.findByUsername(username).orElseThrow();

        UserDTO userDTO = UserDTO.builder()
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .country(user.getCountry())
                .build();

        return ResponseEntity.ok(userDTO);
    }

}
