package com.example.ExpenseManagement.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ExpenseManagement.entities.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private final String secret = "YourSuperSecretKeyForJWTValidationMustBeLongEnough";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    
    private final MyUserDetailService myUserDetails;

    @Autowired
    public JWTService(MyUserDetailService myUserDetails) {
    	this.myUserDetails = myUserDetails;
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("Email", user.getEmail());
        claims.put("phoneNumber", user.getPhoneNo());
        claims.put("roles", user.getRole());

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUserId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*1000))
                .and()
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(User user) {

        Map<String, Object> claims = new HashMap<>();


        claims.put("userId", user.getUserId());
        claims.put("type", "refresh");

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUserId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*60*1000))
                .and()
                .signWith(secretKey)
                .compact();
    }



    public String extractUserId(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {

        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userId = extractUserId(token);
        Claims claims = extractAllClaims(token);

        boolean isAccessToken = !claims.containsKey("type") || !"refresh".equals(claims.get("type"));
        return (userId.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }
    

    public boolean validateRefressToken(String refreshToken) {
        try {

            Claims claims = extractAllClaims(refreshToken);

            boolean isRefreshToken = "refresh".equals(claims.get("type"));

            return !claims.getExpiration().before(new Date()) && isRefreshToken;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }




}
