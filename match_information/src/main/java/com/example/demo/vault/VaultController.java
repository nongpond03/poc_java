package com.example.demo.vault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.VaultException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/content-credential-from-vault")
    public ResponseEntity getContentCredentialFromValue(@RequestBody VaultRequest req) throws VaultException {
        Map<String, Object> resp = service.getUsernamePasswordDatabase(req.getPath());
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/content-from-database")
    public ResponseEntity getContentFromDatabase() {
        List<Transaction> resp = service.getContentFromDatabase();
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
