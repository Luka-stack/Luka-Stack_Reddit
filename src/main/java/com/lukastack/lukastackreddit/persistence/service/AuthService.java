package com.lukastack.lukastackreddit.persistence.service;

import com.lukastack.lukastackreddit.dto.AuthenticationResponse;
import com.lukastack.lukastackreddit.dto.LoginRequest;
import com.lukastack.lukastackreddit.dto.RefreshTokenRequest;
import com.lukastack.lukastackreddit.dto.RegisterRequest;
import com.lukastack.lukastackreddit.error.exceptions.SpringRedditException;
import com.lukastack.lukastackreddit.model.NotificationEmail;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import com.lukastack.lukastackreddit.persistence.entity.VerificationTokenEntity;
import com.lukastack.lukastackreddit.persistence.repository.UserRepository;
import com.lukastack.lukastackreddit.persistence.repository.VerificationTokenRepository;
import com.lukastack.lukastackreddit.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final MailService mailService;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(RegisterRequest registerRequest) {

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(encodePassword(registerRequest.getPassword()));
        newUser.setCreatedDate(Instant.now());
        newUser.setEnabled(false);

        userRepository.save(newUser);
        String token = generateVerificationToken(newUser);
        mailService.sendMail(new NotificationEmail(
                "Activate your Account", newUser.getEmail(),
                "Thank you for signing up to Luka-stack Reddit, "+
                "please click on the url to activate your account :"+
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpiration()))
                .username(loginRequest.getUsername())
                .build();
    }

    @Transactional(readOnly = true)
    public UserEntity getCurrentUser() {

        User userPrincipal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByUsername(userPrincipal.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User '"+ userPrincipal.getUsername() +"' not found"));
    }

    public void verifyAccount(String token) {

        VerificationTokenEntity verificationToken = tokenRepository.findByToken(token).orElseThrow(
                () -> new SpringRedditException("Invalid Token"));
        fetchAndEnableUser(verificationToken);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateToken(refreshTokenRequest.getUsername());

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpiration()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    private void fetchAndEnableUser(VerificationTokenEntity verificationToken) {

        String username = verificationToken.getUser().getUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new SpringRedditException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateVerificationToken(UserEntity user) {

        String token = UUID.randomUUID().toString();
        VerificationTokenEntity verificationToken = new VerificationTokenEntity();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        tokenRepository.save(verificationToken);

        return token;
    }
}
