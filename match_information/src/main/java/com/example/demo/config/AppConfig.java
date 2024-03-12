package com.example.demo.config;

import lombok.val;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
public class AppConfig extends AbstractVaultConfiguration {

    @Override
    public VaultEndpoint vaultEndpoint() {
        val vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setScheme("http");
        vaultEndpoint.setHost("localhost");
        vaultEndpoint.setPort(8200);
        return vaultEndpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication("hvs.z1J4SDR92UVi7KpQHAW2cGYD");
    }
}
