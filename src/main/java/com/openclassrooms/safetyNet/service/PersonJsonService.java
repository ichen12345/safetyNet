package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;

import java.util.List;

public interface PersonJsonService {

    // add a new person
    Person createPerson(Person person);

    Person updatePerson(Person person);

    List<Person> getPerson();

    void deletePerson(Person person);
    
}
