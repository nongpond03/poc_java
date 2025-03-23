package com.example.cloudvault;

import com.example.cloudvault.services.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudVaultApplication implements CommandLineRunner {

    @Autowired
    private DatasourceService sv;

    public static void main(String[] args) {
        SpringApplication.run(CloudVaultApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Credential: " + sv.getDatasourceConfiguration());
    }
}
