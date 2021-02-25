package com.lukastack.lukastackreddit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AuthenticationResponse {

    private String username;
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
}
