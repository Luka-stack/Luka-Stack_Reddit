package com.lukastack.lukastackreddit.controller;

import com.lukastack.lukastackreddit.api.AuthApi;
import com.lukastack.lukastackreddit.dto.AuthenticationResponse;
import com.lukastack.lukastackreddit.dto.LoginRequest;
import com.lukastack.lukastackreddit.dto.RegisterRequest;
import com.lukastack.lukastackreddit.persistence.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<String> signUp(RegisterRequest registerRequest) {
        authService.signUp(registerRequest);
        return ResponseEntity.ok("User successfully registered");
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    public ResponseEntity<String> verifyAccount(String token) {
        authService.verifyAccount(token);
        return ResponseEntity.ok("Account successfully activated");
    }
}
