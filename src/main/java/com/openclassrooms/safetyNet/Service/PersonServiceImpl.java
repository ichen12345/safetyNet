package com.openclassrooms.safetyNet.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.Model.Person;
import com.openclassrooms.safetyNet.Repository.PersonRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        
        Optional<Person> personOptional = personRepository.findById(person.getId());

        if(personOptional.isEmpty()) {
            return null;
        }

        Person originalPerson = personOptional.get();

        if(!StringUtils.isBlank(person.getAddress())){
            originalPerson.setAddress(person.getAddress());
        }
        if(!StringUtils.isBlank(person.getCity())){
            originalPerson.setCity(person.getCity());
        }
        if(!StringUtils.isBlank(person.getZip())){
            originalPerson.setZip(person.getZip());
        }
        if(!StringUtils.isBlank(person.getPhone())){
            originalPerson.setPhone(person.getPhone());
        }
        if(!StringUtils.isBlank(person.getEmail())){
            originalPerson.setEmail(person.getEmail());
        }

        return personRepository.save(originalPerson);

    }

    @Override
    public void deletePerson(Integer id) {
        Person person = new Person();
        person.setId(id);
        personRepository.delete(person);
    }
    
}
