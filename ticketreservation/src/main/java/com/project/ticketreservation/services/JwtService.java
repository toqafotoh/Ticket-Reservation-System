package com.project.ticketreservation.services;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${token.secret.key}")
    String jwtSecretKey;

    @Value("${token.expiration}")
    Long jwtExpirationMs;

    public String extractNationalId(String token) {
        return (String) extractSubject(token);
    }

    public String extractName(String token) {
        return (String) extractClaim(token, "name");
    }

    public String extractEmail(String token) {
        return (String) extractClaim(token, "email");
    }

    public String extractRole(String token) {
        return (String) extractClaim(token, "role");
    }

    public String extractImage(String token) {
        return (String) extractClaim(token, "image");
    }
    
    public String extractNationality(String token) {
        return (String) extractClaim(token, "nationality");
    }

    public String extractGender(String token) {
        return (String) extractClaim(token, "gender");
    }

    public int extractLoyalityPoints(String token) {
        return (int) extractClaim(token, "loyalityPoints");
    }

    public String generateToken(Account userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token) {
        String data = extractSubject(token);
        return !data.isEmpty() && !isTokenExpired(token);
    }
    
    public boolean isTokenDataValid(String token, Account userDetails) {
        final String nationalId = extractNationalId(token);
        return (nationalId.equals(userDetails.getNationalId())) && !isTokenExpired(token);
    }

    private Object extractClaim(String token, String claimsKey) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimsKey);
    }

    private String extractSubject(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private String generateToken(Map<String, Object> extraClaims, Account accountDetails) {
        Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expirationTime = Instant.now().truncatedTo(ChronoUnit.SECONDS).plusMillis(jwtExpirationMs);

        return Jwts.builder()
                .subject(accountDetails.getNationalId())
                .claim("name", accountDetails.getName())
                .claim("email", accountDetails.getUsername())
                .claim("role", accountDetails.getRole().name())
                .claim("image", accountDetails.getAccountImage())
                .claim("nationality", accountDetails.getNationality())
                .claim("gender", accountDetails.getGender())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(Date.from(Instant.now()));
    }

    private Date extractExpiration(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
