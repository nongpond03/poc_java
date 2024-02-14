package com.example.demo.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Provider;

@RestController
@RequestMapping(path = "/api/v1/security")
public class SecurityController {

    private SecurityService service;

    @Autowired
    public SecurityController(SecurityService service) {
        this.service = service;

    }

    @GetMapping
    public Provider[] getSecurityProviders() {
        return service.getSecurityProviders();
    }

    @PostMapping("/aes/encrypt")
    public String encryptAES(@RequestBody AESRequest req) throws JsonProcessingException {
        String json = JsonUtil.toJson(req);
        return service.encryptAES(json);
    }

    @PostMapping("/aes/decrypt")
    public String decryptAES(@RequestBody DecryptAESRequest req) throws JsonProcessingException {
        return new String(service.decryptAES(req.getData()), StandardCharsets.UTF_8);
    }
}
