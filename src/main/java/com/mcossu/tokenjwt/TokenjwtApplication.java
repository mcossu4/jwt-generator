package com.mcossu.tokenjwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class TokenjwtApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenjwtApplication.class);

    public static void main(String[] args) {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info(generateToken("pecroot", "Intesi Group S.p.a.", "VUdoa1lLem5uR1RqQVlzQmNyZjY1a0ZxZ3RrdHVjM1U"));
        }
    }

    public static String generateToken(String uid, String issuer, String secret) {
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
