package com.example.ExpenseManagement.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    
    private final String secret = "YourSuperSecretKeyForJWTValidationMustBeLongEnough";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    
    
    private final MyUserDetailService myUserDetails;

    @Autowired
    public JWTService(MyUserDetailService myUserDetails) {
        this.myUserDetails = myUserDetails;
    }

    public String generateAccessToken(User user) {
        logger.info("Generating access token for user: {}", user.getUserId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("Email", user.getEmail());
        claims.put("phoneNumber", user.getPhoneNo());
        claims.put("roles", user.getRole());

        String token = Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUserId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .and()
                .signWith(secretKey)
                .compact();
        
        logger.info("Access token generated successfully");
        return token;
    }

    public String generateRefreshToken(User user) {
        logger.info("Generating refresh token for user: {}", user.getUserId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("type", "refresh");

        String token = Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUserId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 1000))
                .and()
                .signWith(secretKey)
                .compact();
        
        logger.info("Refresh token generated successfully");
        return token;
    }

    public String extractUserId(String token) {
        logger.debug("Extracting user ID from token");
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            logger.error("Error extracting user ID from token", e);
            return null;
        }

    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            logger.error("Invalid JWT token", e);
            throw e;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        logger.debug("Validating token for user: {}", userDetails.getUsername());
        try {
            final String userId = extractUserId(token);
            Claims claims = extractAllClaims(token);
            boolean isAccessToken = !claims.containsKey("type") || !"refresh".equals(claims.get("type"));
            return userId.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean validateRefreshToken(String refreshToken) {
        logger.debug("Validating refresh token");
        try {
            Claims claims = extractAllClaims(refreshToken);
            boolean isRefreshToken = "refresh".equals(claims.get("type"));
            boolean isValid = !claims.getExpiration().before(new Date()) && isRefreshToken;
            if (isValid) {
                logger.info("Refresh token is valid");
            } else {
                logger.warn("Refresh token is invalid or expired");
            }
            return isValid;
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Error validating refresh token", e);
            return false;
        }
    }
}
