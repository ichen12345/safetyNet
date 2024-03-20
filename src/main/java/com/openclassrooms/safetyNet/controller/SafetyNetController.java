package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.service.SafetyNetJsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class SafetyNetController {
    private Logger logger = LoggerFactory.getLogger(SafetyNetController.class);
    @Autowired
    SafetyNetJsonService safetyNetJsonService;

    // http://localhost:8080/firestation?stationNumber=1&name=Ivy
    @GetMapping("/fireStation")
    public Map<String, Object> getPeopleFromFireStation(@RequestParam("stationNumber") String station) {
        logger.info("GET request for /fireStation endpoint to get people from fire station {}", station);
        return safetyNetJsonService.personServicedFireStation(station);
    }

    @GetMapping("/childAlert")
    public Map<String, Object> getChildAlert(@RequestParam String address) {
        logger.info("GET request for /childAlert endpoint to get a list of children at that address");
        return safetyNetJsonService.childrenFromAddress(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPersonPhoneNumberBasedOnStation(@RequestParam("firestation") String station) {
        logger.info("GET request for /phoneAlert endpoint to get a list of phone numbers of each person within the fire station’s {} jurisdiction", station);
        return safetyNetJsonService.getPersonPhoneFromStation(station);
    }

    @GetMapping("/fire")
    public Map<String, Object> getStationAndPersonBasedOnAddress(@RequestParam String address) {
        logger.info("GET request for /fire endpoint to get the fire station number that services the provided address {} as well as a list of all of the people living at the address", address);
        return safetyNetJsonService.getFireStationAndPeopleFromAddress(address);
    }

    @GetMapping("/flood/stations")
    public Map<String, Object> getHouseholdByStation(@RequestParam String stations) {
        logger.info("GET request for /flood/stations endpoint to get a list of all the households in each fire station’s {} jurisdiction", stations);
        return safetyNetJsonService.getHouseholdByStation(Arrays.asList(stations.split("-")));
    }

    @GetMapping("/personInfo")
    public List<Map<String, Object>> getPerson(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("GET request for /personInfo endpoint to get the person’s name, address, age, email, list of medications with dosages and allergies");
        return safetyNetJsonService.getPersonByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailBasedOnCity(@RequestParam String city) {
        logger.info("GET request for /communityEmail endpoint to get the email addresses of all of the people in the city");
        return safetyNetJsonService.allEmailFromCity(city);
    }
}
