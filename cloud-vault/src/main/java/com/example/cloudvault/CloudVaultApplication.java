package com.example.cloudvault;

import com.example.cloudvault.config.VaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({VaultConfig.class})
public class CloudVaultApplication implements CommandLineRunner {

	private VaultConfig vaultConfig;

	public CloudVaultApplication(VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;

	}

	public static void main(String[] args) {
		SpringApplication.run(CloudVaultApplication.class, args);
		System.out.println("Hello World");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("username: " + vaultConfig.getUsername());
		System.out.println("password: " + vaultConfig.getPassword());
	}
}
