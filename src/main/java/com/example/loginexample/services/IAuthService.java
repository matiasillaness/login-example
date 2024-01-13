package com.example.loginexample.services;

import com.example.loginexample.models.request.LoginRequest;
import com.example.loginexample.models.request.RegisterRequest;
import com.example.loginexample.models.response.AuthResponse;

public interface IAuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
