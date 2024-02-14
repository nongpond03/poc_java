package com.example.demo.atomic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/atomic")
public class AtomicController {

    @Autowired
    private AtomicService service;

    @GetMapping("/v1")
    public ResponseEntity getAtomicV1() throws InterruptedException {
        service.getAtomicV1();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/v2")
    public ResponseEntity getAtomicV2() throws InterruptedException {
        service.getAtomicV2();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/v3")
    public ResponseEntity getAtomicV3() throws InterruptedException {
        service.getAtomicV3();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/v4")
    public ResponseEntity getAtomicV4() throws InterruptedException {
        service.getAtomicV4();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
