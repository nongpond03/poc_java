package com.example.demo.fcd;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class FCDService {

    private final int MAX_VALUE = 9999;
    private final AtomicInteger currentValue = new AtomicInteger(1);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public String getReferenceNo(String prefix) {
        String formattedDateTime = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("formattedDateTime: " + formattedDateTime);
        int runningNumber = getNextValue();
        String randomNumber = RandomStringUtils.randomAlphabetic(1);
        System.out.println("randomNumber: " + randomNumber);
        String referenceNo = String.format("%s%04d", formattedDateTime, runningNumber);
        System.out.println("referenceNo: " + referenceNo);
        //String encode = new BigInteger(referenceNo, 10).toString(Character.MAX_RADIX);
        String encode = new BigInteger(referenceNo, 10).toString(Character.MAX_RADIX);
        System.out.println("encode: " + encode);
        return prefix + encode + randomNumber;
    }

    public UUID randomUUID() {
        return UUID.randomUUID();
    }

    private int getNextValue() {
        int currentValueInt = currentValue.getAndIncrement();
        if (currentValueInt > MAX_VALUE) {
            currentValue.set(0);
            currentValueInt = currentValue.getAndIncrement();
        }
        return currentValueInt;
    }
}
