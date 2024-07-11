package com.example.demo.redirect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedirectService {

    public String kcc() {
        return "https://sandbox.api.servicekrungsrigroup.com/oauth2/authorize" +
                "?client_id=6rfVh88GwaWHq2WBhGxMNmFlD8kfUU8W" +
                "&code_challenge=b2f939f5eae8e439abdb60f0d6569a2d14b93697d6675577348c8969" +
                "&code_challenge_method=S256" +
                "&merchant_customer_id=123456" +
                "&redirect_url=https://httpbin.org/anything" +
                "&response_type=code" +
                "&card_entity=KCC";
    }
}
