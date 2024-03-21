package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Data;
import com.openclassrooms.safetyNet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
@Service
public class PersonJsonServiceImpl implements PersonJsonService{

    @Autowired
    Data data;

    @Override
    public Person createPerson(Person person) {
        List<Person> personList = data.getPersons();
        personList.add(person);
        return person;
    }


    @Override
    public Person updatePerson(Person person) {
        for(Person personData : data.getPersons()) {
            if(personData.getFirstName().equals(person.getFirstName()) && personData.getLastName().equals(person.getLastName())) {
                personData.setCity(person.getCity());
                personData.setEmail(person.getEmail());
                personData.setZip(person.getZip());
                personData.setPhone(person.getPhone());
                personData.setAddress(person.getAddress());
                return personData;
            }

        }
        return null;
    }

    @Override
    public List<Person> getPerson() {
        return data.getPersons();
    }

    @Override
    public void deletePerson(Person person) {
        List<Person> people = data.getPersons();
//        for(Person personData : people) {
//            if(personData.getFirstName().equals(person.getFirstName()) && personData.getLastName().equals(person.getLastName())) {
//                people.remove(personData);
//            }
//        }
        //need to use iterator here
        Iterator<Person> it = people.iterator();
        while (it.hasNext()) {
            Person personData = it.next();
            if (personData.getFirstName().equals(person.getFirstName()) && personData.getLastName().equals(person.getLastName())) {
                it.remove();
            }
        }

    }
}
