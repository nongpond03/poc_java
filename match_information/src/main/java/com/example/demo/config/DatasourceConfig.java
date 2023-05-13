package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public interface DatasourceConfig {
    public void setup();
}