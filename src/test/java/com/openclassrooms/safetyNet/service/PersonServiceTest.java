package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.entity.Person;
import com.openclassrooms.safetyNet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;

    @BeforeEach
    public void setup() {
        person = Person.builder()
                .id(1)
                .firstName("John")
                .lastName("Boyd")
                .email("abc@gmail.com")
                .zip("12345")
                .phone("841-874-6512")
                .address("1509 Culver St")
                .city("Culver")
                .build();
    }

    @DisplayName("JUnit test for createPerson method")
    @Test
    public void givenPersonObject_whenCreatePerson_thenReturnPersonObject() {
        //what does this mean? we don't need the below line right?
//        Mockito.lenient().when(personRepository.findByFirstName(person.getFirstName())).thenReturn(null);
        Mockito.lenient().when(personRepository.save(person)).thenReturn(person);

        Person savedPerson = personService.createPerson(person);

        assertThat(savedPerson).isNotNull();
    }


    @DisplayName("JUnit test for updatePerson method")
    @Test
    public void givenPersonObject_whenUpdatePerson_thenReturnUpdatedPerson() {
        // idk why after I add the line below the test passed...
        Mockito.lenient().when(personRepository.findById(1)).thenReturn(Optional.ofNullable(person));
        Mockito.lenient().when(personRepository.save(person)).thenReturn(person);
        person.setEmail("update@gmail.com");
        person.setAddress("1234 updated street");

        Person updatedPerson = personService.updatePerson(person);
        assertThat(updatedPerson.getEmail()).isEqualTo("update@gmail.com");
        assertThat(updatedPerson.getAddress()).isEqualTo("1234 updated street");
    }

    @DisplayName("JUnit test for deletePerson method")
    @Test
    public void givenPersonId_whenDeletePerson_thenNothing() {
        // don't understand...
//        Integer personId = 1;
        Mockito.lenient().when(personRepository.findById(1)).thenReturn(Optional.ofNullable(person));

//        willDoNothing().given(personRepository).deleteById(personId);

//        personService.deletePerson(personId);

        assertAll(() ->personService.deletePerson(1));
//        verify(personRepository, times(1)).deleteById(personId);
    }
}