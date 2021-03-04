package com.lukastack.lukastackreddit.security;

import com.lukastack.lukastackreddit.error.ErrorCode;
import com.lukastack.lukastackreddit.error.exceptions.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;
import static io.jsonwebtoken.Jwts.parserBuilder;
import static java.util.Date.from;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpiration;

    @PostConstruct
    public void init() {

        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/lukastackreddit.jks");
            keyStore.load(resourceAsStream, "password".toCharArray());
        }
        catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringRedditException("Exception occurred while loading keystore", ErrorCode.AUTH_ERROR);
        }
    }

    public String generateToken(Authentication authentication) {

        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpiration)))
                .compact();
    }

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpiration)))
                .compact();
    }

    public Long getJwtExpiration() {
        return jwtExpiration;
    }

    public boolean validateToken(String jwToken) {
        Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwToken);
        return true;
    }

    public String getUsernameFromJWT(String jwToken) {

        Claims claims = parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(jwToken)
                .getBody();

        return claims.getSubject();
    }

    private PublicKey getPublicKey() {

        try {
            return keyStore.getCertificate("lukastack").getPublicKey();
        }
        catch (KeyStoreException e) {
            throw new SpringRedditException("Exception occurred while retrieving public key", ErrorCode.AUTH_ERROR);
        }
    }

    private PrivateKey getPrivateKey() {

        try {
            return (PrivateKey) keyStore.getKey("lukastack", "password".toCharArray());
        }
        catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringRedditException("Exception occurred while retrieving public key", ErrorCode.AUTH_ERROR);
        }
    }

}
