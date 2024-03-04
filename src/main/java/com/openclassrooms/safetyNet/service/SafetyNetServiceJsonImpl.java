package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SafetyNetServiceJsonImpl implements SafetyNetJsonService{

    @Autowired
    private Data data;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    @Override
    public Map<String, Object> personServicedFireStation(String station) {
        Map<String, Object> summaryMap = new HashMap<>();
        Map<String, String> personMap = new HashMap<>();
        List<Map<String, String>> persons = new ArrayList<>();
        List<FireStation> fireStations = data.getFirestations();
        int adultCount = 0;
        int childCount = 0;

        for(FireStation fireStation : fireStations) {
            if(station.equals(fireStation.getStation())) {
                for(Person person : data.getPersons()) {
                    if(person.getAddress().equals(fireStation.getAddress())) {
                        personMap.put("firstName", person.getFirstName());
                        personMap.put("lastName", person.getLastName());
                        personMap.put("address", person.getAddress());
                        personMap.put("phone", person.getPhone());
                        persons.add(personMap);
                        for(MedicalRecord medicalRecord : data.getMedicalrecords()) {
                            if(medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                                if(Period.between(LocalDate.now(), LocalDate.parse(medicalRecord.getBirthdate(), formatter)).getYears() > 18) {
                                    adultCount++;
                                } else {
                                    childCount++;
                                }
                            }
                        }
                    }

                }
            }
        }
        summaryMap.put("persons", persons);
        summaryMap.put("adults", adultCount);
        summaryMap.put("children", childCount);
        return summaryMap;
    }

}
