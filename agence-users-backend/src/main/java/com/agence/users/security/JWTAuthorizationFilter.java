package com.agence.users.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtUtils.AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith(JwtUtils.PREFIX)) {
            String jwt = authHeader.substring(JwtUtils.PREFIX.length());
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtUtils.SECRET)
                        .parseClaimsJws(jwt)
                        .getBody();

                String username = claims.getSubject();
                List<SimpleGrantedAuthority> authorities = authoritiesFromClaims(claims);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private static List<SimpleGrantedAuthority> authoritiesFromClaims(Claims claims) {
        Object rolesClaim = claims.get("roles");
        if (!(rolesClaim instanceof List<?> raw)) {
            return new ArrayList<>();
        }
        return raw.stream()
                .filter(e -> e != null)
                .map(e -> new SimpleGrantedAuthority(e.toString()))
                .collect(Collectors.toList());
    }
}
