package com.example.demo.person;

import static org.assertj.core.api.Assertions.assertThat;

import net.minidev.json.JSONArray;
import org.assertj.core.api.AssertionsForClassTypes;
import org.json.JSONObject;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService service;

    @Test
    void ItShouldGetPersonsCorrectly() throws Exception{
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("1101500", "Q", "A", "Japanese", "AB314"));
        String expected = "[{\"cId\":\"1101500\",\"name\":\"Q\",\"surname\":\"A\",\"nationality\":\"Japanese\",\"passportNo\":\"AB314\"}]";

        when(service.getPersons()).thenReturn(persons);
        MockHttpServletResponse resp = mvc.perform(
                        get("/api/v1/person")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(resp.getContentAsString()).isEqualTo(expected);
    }
}
