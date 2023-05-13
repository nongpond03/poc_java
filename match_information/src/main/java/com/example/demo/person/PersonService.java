package com.example.demo.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    List<IdentityCard> identityCards = new ArrayList<IdentityCard>();
    List<Passport> passports = new ArrayList<Passport>();

    private PersonRepository PersonRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.PersonRepository = personRepository;
    }

    public List<Person> getPersons() {
        return PersonRepository.findAll();
    }

    public void createPerson(PersonRequest req) {
        for (int x = 0; x < req.identityCards().size(); x++) {
            for (int y = 0; y < req.passports().size(); y++) {
                if (!(req.identityCards().get(x).cId().equals(req.passports().get(y).cId()))) {
                    continue;
                }
                if (!(req.identityCards().get(x).nationality()
                        .equals(req.passports().get(y).nationality()))) {
                    continue;
                }
                PersonRepository.save(
                        new Person(
                                req.identityCards().get(x).cId(),
                                req.identityCards().get(x).name(),
                                req.identityCards().get(x).surname(),
                                req.identityCards().get(x).nationality(),
                                req.passports().get(y).passportNo()));

            }
        }
    }
}
