package com.openclassrooms.safetyNet.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Model.MedicalRecord;
import com.openclassrooms.safetyNet.Model.Person;
import com.openclassrooms.safetyNet.Service.FireStationService;
import com.openclassrooms.safetyNet.Service.PersonService;
import com.openclassrooms.safetyNet.Service.SafetyNetService;

@RestController
public class SafetyNetController {

    @Autowired
    SafetyNetService safetyNetService;

    // http://localhost:8080/firestation?stationNumber=1&name=Ivy
    @GetMapping("/firestation")
    public List<Person> getPeopleFromFireStation(@RequestParam("stationNumber") String station) {
        return safetyNetService.personServicedFirestation(station);
    }

    @GetMapping("/childAlert")
    public List<Person> getChildAlert(@RequestParam String address) {
        return safetyNetService.childrenFromAddressAlert(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPersonPhoneNumberBasedOnStation(@RequestParam("firestation") String station) {

        List<Person> personStation = safetyNetService.personServicedFirestation(station);
        List<String> phoneStation = new ArrayList<>();
        for (Person person : personStation) {
            phoneStation.add(person.getPhone());
        }

        return phoneStation;
    }

    @GetMapping("/fire")
    public List<Person> getStationAndPersonBasedOnAddress(@RequestParam String address) {

    }

    @GetMapping("/stations")
    public List<String> getHouseholdByStation(@RequestParam List<FireStation> stations) {

    }

    @GetMapping("/personInfo")
    public List<Person> getPerson(@RequestParam String firstName, @RequestParam String lastName) {
        return safetyNetService.getPersonByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailBasedOnCity(@RequestParam String city) {
        return safetyNetService.allEmailFromCity(city);
    }
}
