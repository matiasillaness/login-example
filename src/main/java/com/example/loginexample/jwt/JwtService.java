package com.example.loginexample.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
@Slf4j
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigningKey())
                .compact();
    }
    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try{
            final String username=getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
        }catch (Exception e){
            log.error("Error al validar el token: {}", e.getMessage());
            return false;
        }
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaim(token, Claims::getExpiration);
    }
    public boolean isTokenExpired(String token) {
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
