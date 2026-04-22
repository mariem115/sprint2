package com.agence.users.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtils {

    public static final String SECRET = "mySecretKey1234567890";
    public static final long EXPIRES_AT = 60 * 60 * 1000L;
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    public static String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_AT))
                .claim("roles", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
