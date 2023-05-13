package com.example.demo.happyNumber;

import org.springframework.stereotype.Service;

@Service
public class HappyNumberService {

    public HappyNumberService() {
    }

    public boolean isHappyNumber(int input) {
        String number = Integer.toString(input);
        String[] x = number.split("");
        int sum = 0;

        while (true) {
            for (int y = 0; y <= x.length - 1; y++) {
                int i = Integer.parseInt(x[y]);
                sum = sum + (int) Math.pow(Double.valueOf(i), 2);
            }

            if (sum == 1) {
                return true;
            }

            if (sum == 4) {
                return false;
            }

            number = Integer.toString(sum);
            x = number.split("");
            sum = 0;
        }
    }

}
