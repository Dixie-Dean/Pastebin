package com.dixie.pastebin.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtManager {
    private static final long EXPIRATION_TIME = 1000 * 60 * 24;

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(Authentication authentication) {
        var currentDate = new Date();
        var expirationDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
