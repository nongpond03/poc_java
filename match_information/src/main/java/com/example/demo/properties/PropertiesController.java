package com.example.demo.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/property")

public class PropertiesController {

    private PropertiesService service;

    @Autowired
    public PropertiesController(PropertiesService service) {
        this.service = service;
    }

    @GetMapping
    public String getProperty(@RequestParam String key) {
        return service.getProperty(key);
    }
}
