package com.lukastack.lukastackreddit.controller;

import com.lukastack.lukastackreddit.api.AuthApi;
import com.lukastack.lukastackreddit.dto.AuthenticationResponse;
import com.lukastack.lukastackreddit.dto.LoginRequest;
import com.lukastack.lukastackreddit.dto.RefreshTokenRequest;
import com.lukastack.lukastackreddit.dto.RegisterRequest;
import com.lukastack.lukastackreddit.persistence.service.AuthService;
import com.lukastack.lukastackreddit.persistence.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<String> signUp(RegisterRequest registerRequest) {
        authService.signUp(registerRequest);
        return ResponseEntity.ok("User successfully registered");
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    public ResponseEntity<String> verifyAccount(String token) {
        authService.verifyAccount(token);
        return ResponseEntity.ok("Account successfully activated");
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenService) {
        return authService.refreshToken(refreshTokenService);
    }

    @Override
    public ResponseEntity<String> logout(@Valid RefreshTokenRequest refreshTokenRequest) {

        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok("Refresh Token successfully deleted");
    }
}
