package com.example.demo.person;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    @Test
    public void whenCreatePersonCalledVerified() {
        PersonRequest req = new PersonRequest(null, null);
        PersonService service = mock(PersonService.class);
        PersonRepository repo = mock(PersonRepository.class);
        doNothing().when(service).createPerson(isA(PersonRequest.class));
        service.createPerson(req);
        verify(service, times(1)).createPerson(req);
        verify(repo, times(1)).save(any(Person.class));
    }
}
