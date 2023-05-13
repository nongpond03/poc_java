package com.example.demo.happyNumber;

import com.example.demo.fibonacci.FibonacciController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(HappyNumberController.class)
class HappyNumberControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private HappyNumberService service;

    @Test
    void ItShouldReturnAHappyNumberWhenInputIsValid() throws Exception{
        when(service.isHappyNumber(6)).thenReturn(true);
        MockHttpServletResponse resp = mvc.perform(
                        get("/api/v1/happyNumber?number=6")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(resp.getContentAsString()).isEqualTo("true");
    }

    @Test
    void ItShouldReturnErrorBadRequestWhenInputIsInvalid() throws Exception{
        MockHttpServletResponse resp = mvc.perform(
                        get("/api/v1/happyNumber?number=easyworm")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}