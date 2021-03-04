package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.AuthenticationResponse;
import com.lukastack.lukastackreddit.dto.LoginRequest;
import com.lukastack.lukastackreddit.dto.RefreshTokenRequest;
import com.lukastack.lukastackreddit.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/auth")
public interface AuthApi {

    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    AuthenticationResponse login(@RequestBody LoginRequest loginRequest);

    @GetMapping("/accountVerification/{token}")
    ResponseEntity<String> verifyAccount(@PathVariable String token);

    @PostMapping("/refreshToken")
    AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest);

    @PostMapping("/logout")
    ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest);
}
