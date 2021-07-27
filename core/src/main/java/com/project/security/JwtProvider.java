package com.project.security;

import com.project.security.enums.TokenType;
import com.project.security.enums.UserRole;
import com.project.security.exceptions.NotBearerTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.validtime.access}")
    private Long accessValidTime;

    @Value("${application.jwt.validtime.refresh}")
    private Long refreshValidTime;


    public String parseToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        } else
            throw new NotBearerTokenException();

    }

    public GrantedAuthority getAuthorities(Claims claims) {
        String role = claims.get("role", String.class);
        return new SimpleGrantedAuthority(role);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public long getUserIdFromToken(String token) {
        return getClaimsFromToken(token).get("id", Long.class);
    }

    public String getToken(Long id, UserRole role, TokenType type) {
        if (type.equals(TokenType.ACCESS))
            return generateToken(id, role, accessValidTime);
        else
            return generateToken(id, role, refreshValidTime);
    }

    public Authentication getAuthentication(String token) {
        Claims claim = getClaimsFromToken(token);
        return new UsernamePasswordAuthenticationToken(getUserIdFromToken(token), "", List.of(getAuthorities(claim)));
    }

    private String generateToken(Long id, UserRole role, Long validTime) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + validTime);
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UserRole getRoleFromToken(String token) {
        return UserRole.valueOf(getClaimsFromToken(token).get("role", String.class));
    }

    public boolean validateToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().after(new Date());
    }
}
