package com.example.demo.validator.Today.today;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TodayConstraintValidator implements ConstraintValidator<TodayConstraint, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate now = LocalDate.now();
            return localDate.isEqual(now);
        } catch (Exception dte) {
            return false;
        }
    }
}
