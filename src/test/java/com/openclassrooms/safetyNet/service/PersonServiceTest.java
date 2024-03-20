package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.model.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

   @Mock
    Data data;

    @InjectMocks
    private PersonJsonServiceImpl personService;

    private Person person;

    private List<Person> personList;

    @BeforeEach
    public void setup() {
        person = new Person();
        person.setFirstName("first");
        person.setLastName("last");
        person.setPhone("841-874-6512");
        person.setZip("12345");
        person.setAddress("address");
        person.setCity("Taipei");
        person.setEmail("email");

        personList = new ArrayList<>();
        personList.add(person);

    }

    @DisplayName("JUnit test for createPerson method")
    @Test
    public void givenPersonObject_whenCreatePerson_thenReturnPersonObject() {
        Mockito.lenient().when(data.getPersons()).thenReturn(personList);

        Person savedPerson = personService.createPerson(person);

        assertThat(savedPerson).isNotNull();
    }


    @DisplayName("JUnit test for updatePerson method")
    @Test
    public void givenPersonObject_whenUpdatePerson_thenReturnUpdatedPerson() {
        Mockito.lenient().when(data.getPersons()).thenReturn(personList);
        person.setEmail("update@gmail.com");
        person.setAddress("1234 updated street");

        Person updatedPerson = personService.updatePerson(person);
        assertThat(updatedPerson.getEmail()).isEqualTo("update@gmail.com");
        assertThat(updatedPerson.getAddress()).isEqualTo("1234 updated street");
    }

//    @DisplayName("JUnit test for deletePerson method")
//    @Test
//    public void givenPersonId_whenDeletePerson_thenNothing() {
////        List<Person> mockPersonList = Mockito.mock(List<Person.class>);
//        Mockito.lenient().when(data.getPersons()).thenReturn(personList);
//
//        Mockito.lenient().when(personList.remove(person)).thenReturn(true);
//        personService.deletePerson(person);
//
//        assert !personList.contains(person);
//        verify(data, times(1)).getPersons().remove(person);
//    }
}
