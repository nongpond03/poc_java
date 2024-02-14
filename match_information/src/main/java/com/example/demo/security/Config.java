package com.example.demo.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("demo.security.aes")
@Data
public class Config {
    private String secretKey;
}
