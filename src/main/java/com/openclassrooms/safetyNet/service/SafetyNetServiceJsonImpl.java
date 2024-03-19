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
public class SafetyNetServiceJsonImpl implements SafetyNetJsonService {

    @Autowired
    Data data;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public Map<String, Object> personServicedFireStation(String station) {
        Map<String, Object> summaryMap = new HashMap<>();

        List<Map<String, String>> persons = new ArrayList<>();
        List<FireStation> fireStations = data.getFirestations();
        int adultCount = 0;
        int childCount = 0;

        for (FireStation fireStation : fireStations) {
            if (station.equals(fireStation.getStation())) {
                for (Person person : data.getPersons()) {
                    if (person.getAddress().equals(fireStation.getAddress())) {
                        Map<String, String> personMap = new HashMap<>();
                        personMap.put("firstName", person.getFirstName());
                        personMap.put("lastName", person.getLastName());
                        personMap.put("address", person.getAddress());
                        personMap.put("phone", person.getPhone());
                        persons.add(personMap);
                        for (MedicalRecord medicalRecord : data.getMedicalrecords()) {
                            if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                                if (Period.between(LocalDate.parse(medicalRecord.getBirthdate(), formatter), LocalDate.now()).getYears() > 18) {
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

    @Override
    public Map<String, Object> childrenFromAddress(String address) {
        List<Person> persons = data.getPersons();
        List<Person> adultFromAddress = new ArrayList<>();
        Map<String, Object> childrenAndAdults = new HashMap<>();
        List<Map<String, String>> childs = new ArrayList<>();

        for (Person person : persons) {
            if (person.getAddress().equals(address)) {
                for (MedicalRecord medicalRecord : data.getMedicalrecords()) {
                    if (person.getFirstName().equals(medicalRecord.getFirstName()) && person.getLastName().equals(medicalRecord.getLastName())) {
                        int age = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), formatter), LocalDate.now()).getYears();
                        Map<String, String> children = new HashMap<>();
                        if (age <= 18) {
                            children.put("firstName", person.getFirstName());
                            children.put("lastName", person.getLastName());
                            children.put("age", String.valueOf(age));
                            childs.add(children);
                        } else {
                            adultFromAddress.add(person);
                        }
                        break;
                    }

                }

            }
        }
        childrenAndAdults.put("children", childs);
        childrenAndAdults.put("adults", adultFromAddress);
        return childrenAndAdults;
    }

    @Override
    public List<String> getPersonPhoneFromStation(String station) {
        List<FireStation> fireStations = data.getFirestations();
        List<Person> personList = data.getPersons();
        List<String> addresses = new ArrayList<>();
        List<String> phones = new ArrayList<>();

        for (FireStation fireStation : fireStations) {
            if (fireStation.getStation().equals(station)) {
                addresses.add(fireStation.getAddress());
            }
        }
        for (String address : addresses) {
            for (Person person : personList) {
                if (person.getAddress().equals(address)) {
                    phones.add(person.getPhone());
                }
            }
        }
        return phones;
    }

    //working but order messed up
    @Override
    public Map<String, Object> getFireStationAndPeopleFromAddress(String address) {
        Map<String, Object> summary = new HashMap<>();
        List<Map<String, Object>> peopleList = new ArrayList<>();

        for (FireStation fireStation : data.getFirestations()) {
            if (fireStation.getAddress().equals(address)) {
                summary.put("station", fireStation.getStation());
            }
        }
        for (Person person : data.getPersons()) {
            if (person.getAddress().equals(address)) {
                Map<String, Object> personMap = new HashMap<>();
                personMap.put("firstName", person.getFirstName());
                personMap.put("lastName", person.getLastName());
                personMap.put("phone number", person.getPhone());
                for (MedicalRecord medicalRecord : data.getMedicalrecords()) {
                    if (person.getFirstName().equals(medicalRecord.getFirstName()) && person.getLastName().equals(medicalRecord.getLastName())) {
                        int age = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), formatter), LocalDate.now()).getYears();
                        personMap.put("age", String.valueOf(age));
                        personMap.put("medications", medicalRecord.getMedications());
                        personMap.put("allergies", medicalRecord.getAllergies());
                        peopleList.add(personMap);
                    }
                }
            }
        }


        summary.put("persons", peopleList);
        return summary;
    }

    @Override
    public List<Map<String, Object>> getPersonByName(String firstName, String lastName) {
        List<Map<String, Object>> summary = new ArrayList<>();
        for (Person person : data.getPersons()) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                Map<String, Object> persons = new HashMap<>();
                persons.put("firstName", person.getFirstName());
                persons.put("lastName", person.getLastName());
                persons.put("address", person.getAddress());
                persons.put("email", person.getEmail());
                for (MedicalRecord medicalRecord : data.getMedicalrecords()) {
                    if (person.getFirstName().equals(medicalRecord.getFirstName()) && person.getLastName().equals(medicalRecord.getLastName())) {
                        int age = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), formatter), LocalDate.now()).getYears();
                        persons.put("age", String.valueOf(age));
                        persons.put("medications", medicalRecord.getMedications());
                        persons.put("allergies", medicalRecord.getAllergies());
                        summary.add(persons);
                    }
                }
            }
        }

        return summary;
    }
    @Override
    public Map<String, Object> getHouseholdByStation(List<String> stations) {
        List<Map<String, Object>> summary;
        Map<String, Object> stationPerson = new HashMap<>();
        for (String station : stations) {
            summary = new ArrayList<>();
            for (FireStation fireStation : data.getFirestations()) {
                if (fireStation.getStation().equals(station)) {
                    for (Person person : data.getPersons()) {
                        Map<String, Object> personMap = new HashMap<>();
                        if (fireStation.getAddress().equals(person.getAddress())) {
                            personMap.put("phone number", person.getPhone());
                            personMap.put("firstName", person.getFirstName());
                            personMap.put("lastName", person.getLastName());


                            for (MedicalRecord medicalRecord : data.getMedicalrecords()) {
                                if (person.getFirstName().equals(medicalRecord.getFirstName()) && person.getLastName().equals(medicalRecord.getLastName())) {
                                    int age = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), formatter), LocalDate.now()).getYears();
                                    personMap.put("age", String.valueOf(age));
                                    personMap.put("medications", medicalRecord.getMedications());
                                    personMap.put("allergies", medicalRecord.getAllergies());
                                    summary.add(personMap);
                                }

                            }
                        }

                    }
                }



            }


            stationPerson.put("persons from station " + station, summary);
        }



        return stationPerson;
    }

    @Override
    public List<String> allEmailFromCity(String city) {
        List<String> emails = new ArrayList<>();
        for (Person person : data.getPersons()) {
            if (person.getCity().equals(city)) {
                emails.add(person.getEmail());
            }
        }
        return emails;
    }
}