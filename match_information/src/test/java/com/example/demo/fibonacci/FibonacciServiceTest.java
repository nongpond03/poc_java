package com.example.demo.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FibonacciServiceTest {


    @Autowired
    private FibonacciService service;

    @Test
    void itShouldGetAFibonacciNumberCorrectlyWhenInputIs5() {
        int expected = 5;
        int result = 0;
        result = service.fibonacci(5);
        assertEquals(expected, result);
    }

    @Test
    void itShouldGetAFibonacciNumberCorrectlyWhenInputIs6() {
        int expected = 8;
        int result = 0;
        result = service.fibonacci(6);
        assertEquals(expected, result);
    }
}