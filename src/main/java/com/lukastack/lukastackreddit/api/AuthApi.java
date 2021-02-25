package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.AuthenticationResponse;
import com.lukastack.lukastackreddit.dto.LoginRequest;
import com.lukastack.lukastackreddit.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface AuthApi {

    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest);

    @GetMapping("/accountVerification/{token}")
    ResponseEntity<String> verifyAccount(@PathVariable String token);
}
