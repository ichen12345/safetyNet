package com.openclassrooms.safetyNet.Service;

import com.openclassrooms.safetyNet.Model.Person;

public interface PersonService {

    // add a new person
    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Integer id);
    
}
