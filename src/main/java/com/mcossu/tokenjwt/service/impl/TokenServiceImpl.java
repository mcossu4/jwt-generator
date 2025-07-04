package com.mcossu.tokenjwt.service.impl;

import com.mcossu.tokenjwt.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(String uid, String issuer, String secret) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(100, ChronoUnit.MINUTES)))
                .issuer(issuer)
                .subject(uid)
                .claim("uid", uid)
                .signWith(key)
                .compact();
    }
}
