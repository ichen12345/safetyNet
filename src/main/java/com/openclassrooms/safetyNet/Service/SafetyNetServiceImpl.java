package com.openclassrooms.safetyNet.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Model.MedicalRecord;
import com.openclassrooms.safetyNet.Model.Person;
import com.openclassrooms.safetyNet.Repository.FireStationRepository;
import com.openclassrooms.safetyNet.Repository.MedicalRecordRepository;
import com.openclassrooms.safetyNet.Repository.PersonRepository;

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
    public List<Person> personServicedFirestation(String station) {
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        List<Person> personAddress = personRepository.findAll();
        // to return only specific columns in the Person table, I have written sql
        // code in the repo?
        // List<Person> personAddress = personRepository.findFirstLastNameAddressPhone();
        List<Person> peopleAssignedToStation = new ArrayList<>();

        for (FireStation fireStation : fireStations) {
            for (Person person : personAddress) {
                if (person.getAddress().equals(fireStation.getAddress())) {
                    peopleAssignedToStation.add(person);
                }
            }
        }

        return peopleAssignedToStation;
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
        List<Person> personFromAdress = personRepository.findByAddress(address);
        List<Person> childrenFromAddress = new ArrayList<>();
        List<Person> adultsFromAddress = new ArrayList<>();

        for (Person person : personFromAdress) {
            int age = Period.between(medicalRecordRepository.findByBirthDate(person.getMedicalRecord().getBirthDate()),
                    currentDate).getYears();
            if (age <= 18) {
                childrenFromAddress.add(person);
            } else {
                adultsFromAddress.add(person);
            }
        }

        return childrenFromAddress;
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
        List<Person> personFromAddress = personRepository.findByAddress(address);
        // what is the best way to store the people info and the firestation number?

    }

    // include name, phone number, and age of each person, and  any 
// medications (with dosages) and allergies beside each person’s name. 
    @Override
    public List<Person> getHouseholdByStation(List<FireStation> stations) {
        
        for(FireStation fireStation : stations){
            List<Person> personFromStation = personRepository.findByAddress(fireStation.getAddress());
            // how to add the second combo to a new list?
        }
        

    }

    @Override
    public List<Person> getPersonByName(String firstName, String lastName) {
        List<Person> PersonFirstName = personRepository.findByFirstName(firstName);
        for (Person person : PersonFirstName) {
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
    }

    @Override
    public List<String> allEmailFromCity(String city) {
        List<Person> personFromCity = personRepository.findByCity(city);
        List<String> emailFromCity = new ArrayList<>();

        for (Person person : personFromCity) {
            emailFromCity.add(person.getEmail());
        }

        return emailFromCity;
    }

}
