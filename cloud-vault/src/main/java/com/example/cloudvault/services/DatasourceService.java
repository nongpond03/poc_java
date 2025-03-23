package com.example.cloudvault.services;

import com.example.cloudvault.configuration.DatasourceConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatasourceService {

    @Autowired
    private DatasourceConfiguration datasourceConfiguration;

    public String getDatasourceConfiguration() {
        return "username: " + datasourceConfiguration.getUsername() + " password: " + datasourceConfiguration.getPassword();
    }
}
