package com.example.jwt.config.util;

import com.example.jwt.config.JwtConfig;
import com.example.jwt.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

@Component
@Slf4j
public class JwtTokenUtil {

    private JwtConfig jwtConfig;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(format("%s,%s",user.getId(),user.getUsername()))
                .setIssuer(jwtConfig.getJwtIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+7*24*60*60*1000))
                .signWith(SignatureAlgorithm.HS512,jwtConfig.getJwtSecret())
                .compact();
    }

    public String getUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(jwtConfig.getJwtSecret()).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            log.error("Invalid JWT signature - {}",ex.getMessage());
        }catch (MalformedJwtException ex){
            log.error("Invalid JWT token - {}",ex.getMessage());
        }catch (ExpiredJwtException ex){
            log.error("Expired JWT token - {}",ex.getMessage());
        }catch (UnsupportedJwtException ex){
            log.error("Unsupported JWT token - {}",ex.getMessage());
        }catch (IllegalArgumentException ex){
            log.error(" JWT claims string is empty - {}",ex.getMessage());
        }
        return false;
    }


}
