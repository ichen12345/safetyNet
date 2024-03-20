package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonJsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonJsonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    private Person person;

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
    }

    @Test
    public void PersonController_CreatePerson_ReturnCreated() throws Exception {
        when(personService.createPerson(person)).thenReturn(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdatePerson_ReturnUpdatedPerson() throws Exception {
        when(personService.updatePerson(person)).thenReturn(person);

        mockMvc.perform(put("/person", person)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());
    }
    @Test
    public void DeletePersonById_ReturnNothing() throws Exception {
        doNothing().when(personService).deletePerson(person);
        mockMvc.perform(delete("/person", person)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                        .andExpect(status().isOk());

    }

}