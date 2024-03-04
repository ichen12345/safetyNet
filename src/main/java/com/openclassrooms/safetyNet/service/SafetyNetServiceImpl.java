package com.openclassrooms.safetyNet.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.repository.FireStationRepository;
import com.openclassrooms.safetyNet.repository.MedicalRecordRepository;
import com.openclassrooms.safetyNet.repository.PersonRepository;

@Service
public class SafetyNetServiceImpl implements SafetyNetService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FireStationRepository fireStationRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    LocalDate currentDate = LocalDate.now();

    // returns list of people, number of adults in the service area and the number
    // of children
    @Override
    public List<Person> personServicedFireStation(String station) {
        List<com.openclassrooms.safetyNet.entity.FireStation> fireStations = fireStationRepository.findByStation(station);
        List<com.openclassrooms.safetyNet.entity.Person> personAddress = personRepository.findAll();
        // to return only specific columns in the Person table, I have written sql
        // code in the repo?
        // List<Person> personAddress = personRepository.findFirstLastNameAddressPhone();
        List<com.openclassrooms.safetyNet.entity.Person> peopleAssignedToStation = new ArrayList<>();

        for (com.openclassrooms.safetyNet.entity.FireStation fireStation : fireStations) {
            for (com.openclassrooms.safetyNet.entity.Person person : personAddress) {
                if (person.getAddress().equals(fireStation.getAddress())) {
                    peopleAssignedToStation.add(person);
                }
            }
        }

        return null;
    }

    // Servlet.service() for servlet [dispatcherServlet] in context with path []
    // threw exception
    // [Request processing failed: java.lang.NullPointerException: Cannot invoke
    // "com.openclassrooms.safetyNet.Model.MedicalRecord.getBirthDate()" because the
    // return value of
    // "com.openclassrooms.safetyNet.Model.Person.getMedicalRecord()" is null] with
    // root cause
    // when testing in postman, medicalRecord is null. confused with why we fill in
    // medicalId in body
    @Override
    public List<Person> childrenFromAddressAlert(String address) {
        List<com.openclassrooms.safetyNet.entity.Person> personFromAddress = personRepository.findByAddress(address);
        List<com.openclassrooms.safetyNet.entity.Person> childrenFromAddress = new ArrayList<>();
        List<com.openclassrooms.safetyNet.entity.Person> adultsFromAddress = new ArrayList<>();

        for (com.openclassrooms.safetyNet.entity.Person person : personFromAddress) {
            int age = Period.between(medicalRecordRepository.findByBirthDate(person.getMedicalRecord().getBirthDate()),
                    currentDate).getYears();
            if (age <= 18) {
                childrenFromAddress.add(person);
            } else {
                adultsFromAddress.add(person);
            }
        }

        return null;
    }

    // still working on this
    // return the fire station number that services the provided address as well as
    // a list of all of
    // the people living at the address. This list should include each person’s
    // name, phone number, age,
    // medications with dosage, and allergies
    @Override
    public List<Person> getPersonAndStationFromAddress(String address) {
        String station = fireStationRepository.findByAddress(address);
        List<com.openclassrooms.safetyNet.entity.Person> personFromAddress = personRepository.findByAddress(address);
        // what is the best way to store the people info and the fire station number?
        return null;
    }

    // include name, phone number, and age of each person, and  any 
// medications (with dosages) and allergies beside each person’s name. 
    @Override
    public List<Person> getHouseholdByStation(List<FireStation> stations) {
        
        for(FireStation fireStation : stations){
            List<com.openclassrooms.safetyNet.entity.Person> personFromStation = personRepository.findByAddress(fireStation.getAddress());
            // how to add the second combo to a new list?
        }
        return null;

    }

    @Override
    public List<Person> getPersonByName(String firstName, String lastName) {
        List<com.openclassrooms.safetyNet.entity.Person> PersonFirstName = personRepository.findByFirstName(firstName);
        for (com.openclassrooms.safetyNet.entity.Person person : PersonFirstName) {
            if (person.getLastName() == lastName) {
                person.getFirstName();
                person.getLastName();
                person.getAddress();
                int age = Period.between(person.getMedicalRecord().getBirthDate(), currentDate).getYears();

                // return the person’s name, address, age, email, list of medications with
                // dosages and allergies
                person.getMedicalRecord().getMedications();
                person.getMedicalRecord().getAllergies();
            }
        }
        return null;
    }

    @Override
    public List<String> allEmailFromCity(String city) {
        List<com.openclassrooms.safetyNet.entity.Person> personFromCity = personRepository.findByCity(city);
        List<String> emailFromCity = new ArrayList<>();

        for (com.openclassrooms.safetyNet.entity.Person person : personFromCity) {
            emailFromCity.add(person.getEmail());
        }

        return emailFromCity;
    }

}
