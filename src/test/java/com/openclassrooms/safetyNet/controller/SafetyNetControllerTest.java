package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.SafetyNetJsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(SafetyNetControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SafetyNetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SafetyNetJsonService safetyNetJsonService;

    @InjectMocks
    private SafetyNetController safetyNetController;

    @Autowired
    private ObjectMapper objectMapper;

    private Person person;

    private MedicalRecord medicalRecord;

    private FireStation fireStation;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setFirstName("first");
        person.setLastName("last");
        person.setAddress("address");

        fireStation = new FireStation();
        fireStation.setAddress("address");
        fireStation.setStation("3");

        medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setFirstName("first");
        medicalRecord.setLastName("last");

    }

    @Test
    void getPeopleFromFireStation() {
        Map<String, Object> data = new HashMap<>();
        Mockito.when(safetyNetJsonService.personServicedFireStation("3")).thenReturn(data);
        Map<String, Object> result = safetyNetController.getPeopleFromFireStation("3");
        assertNotNull(result);
    }

    @Test
    void getChildAlert() {
    }

    @Test
    void getPersonPhoneNumberBasedOnStation() {
    }

    @Test
    void getStationAndPersonBasedOnAddress() {
    }

    @Test
    void getHouseholdByStation() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void getEmailBasedOnCity() {
    }
}