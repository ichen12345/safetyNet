package com.openclassrooms.safetyNet.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Model.MedicalRecord;
import com.openclassrooms.safetyNet.Model.Person;
import com.openclassrooms.safetyNet.Repository.FireStationRepository;
import com.openclassrooms.safetyNet.Repository.PersonRepository;

@Service
public class SafetyNetServiceImpl implements SafetyNetService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FireStationRepository fireStationRepository;

    // returns list of people, number of adults in the service area and the number
    // of children
    @Override
    public List<Person> personServicedFirestation(String station) {
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        // List<Person> personAddress = personRepository.findAll();
        // to return only specific columns in the Person table, do I need to write sql code here or create an interface?
        List<Person> personAddress = personRepository.getFirstLastNameAddressPhone();
        // getting below error:
        // 2024-02-29T11:35:58.212-05:00 ERROR 35780 --- [nio-8080-exec-5] o.a.c.c.C.[.[.[/].[dispatcherServlet]    
        // : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.dao.InvalidDataAccessResourceUsageException: could not prepare statement [Column "P.FIRSTNAME" not found; SQL statement:
        // select p.firstName, p.lastName, p.address, p.phone from Person p [42122-224]] [select p.firstName, p.lastName, p.address, p.phone from Person p]; 
        // SQL [select p.firstName, p.lastName, p.address, p.phone from Person p]] with root cause
        List<Person> peopleAssignedToStation = new ArrayList<>();

        for (FireStation fireStation : fireStations) {
            for(Person person : personAddress) {
                if(person.getAddress().equals(fireStation.getAddress())) {
                    peopleAssignedToStation.add(person);
                }
            }
        }

        return peopleAssignedToStation;
    }

    // @Override
    // public List<Children> childrenFromAddress(Person person, MedicalRecord medicalRecord) {
    //     List<Children> childrenFromAdress = new ArrayList<>();
    //     person.getAddress();
    //     LocalDate currentDate = LocalDate.now();
    //     int age = Period.between(medicalRecord.getBirthDate(), currentDate).getYears();

    //     if (age <= 18) {
    //         childrenFromAdress.add(person);
    //     }

    //     return childrenFromAdress;
    // }

}
