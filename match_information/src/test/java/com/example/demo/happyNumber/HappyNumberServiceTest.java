package com.example.demo.happyNumber;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HappyNumberServiceTest {

    @Autowired
    private HappyNumberService service;

    @Test
    void itShouldReturnTrueWhenInputIsAHappyNumber() {
        boolean expected = true;
        boolean result = false;
        result = service.isHappyNumber(7);
        assertEquals(expected, result);
    }

    @Test
    void itShouldReturnFalseWhenInputIsNotAHappyNumber() {
        boolean expected = false;
        boolean result = false;
        result = service.isHappyNumber(5);
        assertEquals(expected, result);
    }
}