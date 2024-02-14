package com.example.demo.vault;

import com.bettercloud.vault.VaultException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultVersionedKeyValueTemplate;

import java.util.Map;

@Service
@Slf4j
public class VaultService {

    @Autowired
    private TransactionRepository repo;

    public Map<String, Object> getContentV2FromVault(String path) {
        System.out.println("path: " + path);
        val vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setScheme("http");
        vaultEndpoint.setHost("localhost");
        vaultEndpoint.setPort(8200);
        TokenAuthentication token = new TokenAuthentication("hvs.z1J4SDR92UVi7KpQHAW2cGYD");

        VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, token);
        VaultVersionedKeyValueTemplate kvTemplate = new VaultVersionedKeyValueTemplate(vaultTemplate, "finnet");

        //System.out.println("kvTemplate: " + kvTemplate.get("fcd/dev/database", Versioned.Version.from(4)).getData());
        //System.out.println("kvTemplate: " + kvTemplate.get("fcd/dev/database", Versioned.Version.from(3)).getData());

        return kvTemplate.get(path).getData();
    }

    public Map<String, Object> getUsernamePasswordDatabase(String path) throws VaultException {
        val vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setScheme("http");
        vaultEndpoint.setHost("localhost");
        vaultEndpoint.setPort(8200);
        TokenAuthentication token = new TokenAuthentication("hvs.z1J4SDR92UVi7KpQHAW2cGYD");

        VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, token);
        val data = vaultTemplate.read(path).getData();
        return data;
    }
}
