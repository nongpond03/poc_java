package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;

@Service
@Slf4j
public class SecurityService {
    private AESUtil aesUtil;
    private Config config;

    @Autowired
    public SecurityService(AESUtil aesUtil, Config config) {
        this.aesUtil = aesUtil;
        this.config = config;
    }

    public Provider[] getSecurityProviders() {
        Provider[] providers = Security.getProviders();
        return providers;
    }

    public String encryptAES(String req) {
        log.info("config: " + config.getSecretKey());
        val secretKey = aesUtil.generateAESKey(config.getSecretKey());
        val iv = aesUtil.generateIvParameterSpec(secretKey);
        log.info("secretKey: " + new String(secretKey.getEncoded()));
        val eByte = aesUtil.encryptWithoutIv(req, secretKey);
        return Base64.getEncoder().encodeToString(eByte);
    }

    public byte[] decryptAES(String req) {
        log.info("config: " + config.getSecretKey());
        SecretKey secretKey = aesUtil.generateAESKey(config.getSecretKey());
        val iv = aesUtil.generateIvParameterSpec(secretKey);
        log.info("aesKey: " + new String(secretKey.getEncoded()));
        byte[] decodedReq = Base64.getDecoder().decode(req);
        return aesUtil.decryptWithoutIv(decodedReq, secretKey);
        //return aesUtil.decrypt(decodedReq, aesKey, iv);
    }
}
