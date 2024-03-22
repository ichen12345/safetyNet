package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.service.PersonJsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.openclassrooms.safetyNet.model.Person;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonJsonService personJsonService;

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        logger.info("Post request for /person endpoint to add a {}", person);
        return personJsonService.createPerson(person);
    }

    @PutMapping()
    public Person updatePerson(@RequestBody Person person) {
        logger.info("Put request for /person endpoint to update a person to {}", person);
        if(personJsonService.updatePerson(person) == null) {
            System.out.println("updated person does not exist!");
        }
        return personJsonService.updatePerson(person);
    }

    @GetMapping()
    public List<Person> personList() {
        return personJsonService.getPerson();
    }

    @DeleteMapping()
    public void deletePerson(@RequestBody Person person) {
        logger.info("Delete request for /person endpoint to delete a person with first name {} and last name {}", person.getFirstName(), person.getLastName());
        personJsonService.deletePerson(person);
    }
    
}
