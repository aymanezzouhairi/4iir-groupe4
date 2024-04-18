package com.emsi.Site_de_Reservation.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // Variable d'environnement pour stocker le secret JWT
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String _id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(_id)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            System.out.println(token);
            if (token == null || token.isEmpty()) {
                System.out.println("Token is null or empty.");
                return false;
            }

            if (secret == null || secret.isEmpty()) {
                System.out.println("Secret key is null or empty.");
                return false;
            }
            if (token.startsWith("Bearer ")) { // just for postman
                token = token.substring(7);
            }
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            System.out.println("Error validating token: " + ex.getMessage());
            return false;
        }
    }

    public String getIdFromToken(String token) {
        try {
            if (token.startsWith("Bearer ")) { // just for postman
                token = token.substring(7);
            }
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}