package com.example.demo.pkce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/pkce")
public class PKCEController {

    @Autowired
    private PKCEService service;

    @GetMapping
    public ResponseEntity codeChallenge(@RequestParam(name = "state", required = false) String state) {
        String codeChallenge = service.codeChallenge();
        return ResponseEntity.status(200).body(codeChallenge);
    }
}
