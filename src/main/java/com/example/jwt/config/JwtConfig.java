package com.example.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.jwt")
@Data
public class JwtConfig {
    private String jwtSecret;
    private String jwtIssuer;
}
