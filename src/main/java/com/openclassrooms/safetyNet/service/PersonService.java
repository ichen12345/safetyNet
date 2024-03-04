package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.entity.Person;

public interface PersonService {

    // add a new person
    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Integer id);
    
}
