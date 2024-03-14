package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.model.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SafetyNetServiceJsonImplTest {

    @Mock
    Data data;

    @InjectMocks
    private SafetyNetServiceJsonImpl safetyNetServiceJson;

    ArrayList<Person> persons;

    ArrayList<MedicalRecord> medicalRecords;

    ArrayList<FireStation> fireStations;
    @BeforeEach
    void setup() {
        Person person = new Person();
        person.setCity("Chicago");
        person.setEmail("123");
        person.setPhone("123456789");
        person.setFirstName("first");
        person.setLastName("last");
        person.setAddress("address");
        persons = new ArrayList<>();
        persons.add(person);

        String[] allergies = {"allergy1", "allergy2"};
        String[] med = {"med"};
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("first");
        medicalRecord.setLastName("last");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(Arrays.asList(med));
        medicalRecord.setAllergies(Arrays.asList(allergies));
        medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);

        FireStation fireStation = new FireStation();
        fireStation.setStation("3");
        fireStation.setAddress("address");
        fireStations = new ArrayList<>();
        fireStations.add(fireStation);
    }

    @Test
    void personServicedFireStation() {


    }

    @Test
    void childrenFromAddress() {
    }

    @Test
    void getPersonPhoneFromStation() {
        Mockito.when(data.getFirestations()).thenReturn(fireStations);
        Mockito.when(data.getPersons()).thenReturn(persons);

        List<String> allPhones = safetyNetServiceJson.getPersonPhoneFromStation("3");
        assertEquals("address", fireStations.get(0).getAddress());
        assertTrue(allPhones.get(0).equals("123456789"));
    }

    @Test
    void getFireStationAndPeopleFromAddress() {
    }

    @Test
    void getPersonByName() {
        Mockito.when(data.getPersons()).thenReturn(persons);
        Mockito.when(data.getMedicalrecords()).thenReturn(medicalRecords);

        List<Map<String, Object>> medicalRecord = safetyNetServiceJson.getPersonByName("first", "last");

        String[] allergies = {"allergy1", "allergy2"};
        assertEquals(Arrays.asList(allergies),medicalRecord.get(0).get("allergies"));

        List<Object> objectList = Arrays.asList(medicalRecord.get(0).get("allergies"));
        String [] results = objectList.get(0).toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .split(",");
        System.out.println(Arrays.toString(results));
        assertEquals(2, results.length);
    }

    @Test
    void getHouseholdByStation() {
    }

    @Test
    void allEmailFromCity() {
        Mockito.when(data.getPersons()).thenReturn(persons);
        List<String> emails = safetyNetServiceJson.allEmailFromCity("Chicago");

        assertTrue(emails.get(0).equals("123"));
        Mockito.verify(data, times(1)).getPersons();
    }
}