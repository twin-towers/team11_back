package com.example.hackathon11.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Integer jwtLifetime;

    public String generateToken(UserDetails userDetails) {

        Date issuedDate = new Date();               //  время создания токена
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);    //  время окончания жизни токена
        return Jwts.builder()
                .setSubject(userDetails.getUsername())         //  email пользователя
                .setIssuedAt(issuedDate)                    //  время создания
                .setExpiration(expiredDate)                 //  время окончания жизни
                .signWith(SignatureAlgorithm.HS256, secret) //  подпись
                .compact();                                 //  сборка токена
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String validateJwtToken(String authToken)
    {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
        return "";
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

}
