package com.example.demo.vault;

import com.bettercloud.vault.VaultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/vault")
public class VaultController {

    @Autowired
    private VaultService service;

    @PostMapping("/content-v2-from-vault")
    public ResponseEntity getContentV2FromVault(@RequestBody VaultRequest req) {
        Map<String, Object> resp = service.getContentV2FromVault(req.getPath());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PostMapping("/content-from-database")
    public ResponseEntity getContentFromDatabase(@RequestBody VaultRequest req) throws VaultException {
        Map<String, Object> resp = service.getUsernamePasswordDatabase(req.getPath());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
