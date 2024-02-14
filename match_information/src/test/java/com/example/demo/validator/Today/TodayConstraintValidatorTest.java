package com.example.demo.validator.Today;

import com.example.demo.validator.Today.today.TodayConstraintValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import(TodayConstraintValidator.class)
class TodayConstraintValidatorTest {

    private TodayConstraintValidator validator = new TodayConstraintValidator();
    @Test
    void contextLoads() {
        assertThat(validator).isNotNull();
    }
    @Test
    void should_Return_True_When_Input_Is_Correct() {
        boolean resp = validator.isValid("2023-05-21", null);
        assertThat(resp).isEqualTo(true);
    }

    @Test
    void should_Return_False_When_Input_Is_Wrong_Format() {
        boolean resp = validator.isValid("2023/05/21", null);
        assertThat(resp).isEqualTo(false);
    }
}