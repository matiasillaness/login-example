package com.example.loginexample.services.impl;

import com.example.loginexample.jwt.JwtService;
import com.example.loginexample.models.ERole;
import com.example.loginexample.models.User;
import com.example.loginexample.models.dtos.UserDTO;
import com.example.loginexample.models.request.LoginRequest;
import com.example.loginexample.models.request.RegisterRequest;
import com.example.loginexample.models.response.AuthResponse;
import com.example.loginexample.respository.UserRepository;
import com.example.loginexample.services.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(ERole.USER)
                .build();

        userRepository.save(user);

        log.error("User registered: {}", user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();

        log.error("User login: {}", user);
        String token=jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }


}
