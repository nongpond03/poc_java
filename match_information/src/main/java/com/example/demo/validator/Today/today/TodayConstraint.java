package com.example.demo.validator.Today.today;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TodayConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface TodayConstraint {
    String message() default
            "Effective date is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
