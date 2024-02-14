package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Vault {

    private Database database;

    public static class Database {
        private String username;
        private String password;
    }
}
