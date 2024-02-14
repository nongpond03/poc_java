package com.example.demo.fcd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/fcd")
public class FCDController {

    @Autowired
    private FCDService service;

    @GetMapping("/v1")
    public ResponseEntity getReferenceNo() {
        String ref = service.getReferenceNo("");
        return ResponseEntity.status(HttpStatus.OK).body(ref);
    }
}
