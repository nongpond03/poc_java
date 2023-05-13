package com.example.demo.fibonacci;

import org.springframework.stereotype.Service;

@Service
public class FibonacciService {
    private int a;
    private int b;
    private int c;

    public FibonacciService() {

    }

    public int fibonacci(int n) {
        a = 0;
        b = 1;
        c = 1;

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        for (int i = 2; i < n; i++) {
            a = b;
            b = c;
            c = a + b;
        }

        return c;
    }
}
