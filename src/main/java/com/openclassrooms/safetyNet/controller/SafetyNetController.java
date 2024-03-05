package com.openclassrooms.safetyNet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.openclassrooms.safetyNet.service.SafetyNetJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.SafetyNetService;

@RestController
public class SafetyNetController {

    @Autowired
    SafetyNetService safetyNetService;

    @Autowired
    SafetyNetJsonService safetyNetJsonService;

    // http://localhost:8080/firestation?stationNumber=1&name=Ivy
    @GetMapping("/fireStation")
    public Map<String, Object> getPeopleFromFireStation(@RequestParam("stationNumber") String station) {
        return safetyNetJsonService.personServicedFireStation(station);
    }

    @GetMapping("/childAlert")
    public Map<String, Object> getChildAlert(@RequestParam String address) {
        return safetyNetJsonService.childrenFromAddress(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPersonPhoneNumberBasedOnStation(@RequestParam("firestation") String station) {

        List<Person> personStation = safetyNetService.personServicedFireStation(station);
        List<String> phoneStation = new ArrayList<>();
        for (Person person : personStation) {
            phoneStation.add(person.getPhone());
        }

        return phoneStation;
    }

    @GetMapping("/fire")
    public List<Person> getStationAndPersonBasedOnAddress(@RequestParam String address) {
        return null;
    }

    @GetMapping("/stations")
    public List<String> getHouseholdByStation(@RequestParam List<FireStation> stations) {
        return null;
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
