package com.example.demo.fibonacci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/fibonacci")

public class FibonacciController {

    private FibonacciService service;

    @Autowired
    public FibonacciController(FibonacciService service) {
        this.service = service;
    }

    @GetMapping
    public int GetFibonacci(@RequestParam int number) {
        return service.fibonacci(number);
    }
}
