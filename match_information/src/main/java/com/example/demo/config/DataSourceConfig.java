package com.example.demo.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultOperations;

@Configuration
public class DataSourceConfig {

    @Autowired
    VaultOperations vaultOperations;
    @Value("${spring.datasource.url}")
    private String url;

    @Bean
    public javax.sql.DataSource getDataSource() {
        //val vaultEndpoint = new VaultEndpoint();
        //vaultEndpoint.setScheme("http");
        //vaultEndpoint.setHost("localhost");
        //vaultEndpoint.setPort(8200);
        //TokenAuthentication token = new TokenAuthentication("hvs.z1J4SDR92UVi7KpQHAW2cGYD");

        //VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, token);
        val data = vaultOperations.read("database/creds/my-role").getData();

        System.out.println("data: " + data);

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        assert data != null;
        dataSourceBuilder.username(data.get("username").toString());
        dataSourceBuilder.password(data.get("password").toString());

        return dataSourceBuilder.build();
    }
}
