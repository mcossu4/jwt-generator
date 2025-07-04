package com.mcossu.tokenjwt.web.controller;

import com.mcossu.tokenjwt.service.TokenService;
import com.mcossu.tokenjwt.web.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.uid}")
    private String uid;

    @GetMapping("/api/token")
    public ResponseEntity<TokenResponse> generateToken() {
        String token = tokenService.generateToken(uid, issuer, secret);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}