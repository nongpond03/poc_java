package com.example.demo.paratheses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/paratheses")

public class ParathesesController {

    private ParathesesService service;

    @Autowired
    public ParathesesController(ParathesesService service) {
        this.service = service;
    }

    @GetMapping
    public boolean isMatch(@RequestParam String input) {
        return service.isMatch(input);
    }
}
