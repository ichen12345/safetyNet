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
        logger.info("GET request for /childAlert endpoint to get  a list of children at that address");
        return safetyNetJsonService.childrenFromAddress(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPersonPhoneNumberBasedOnStation(@RequestParam("firestation") String station) {
        return safetyNetJsonService.getPersonPhoneFromStation(station);
    }

    @GetMapping("/fire")
    public Map<String, Object> getStationAndPersonBasedOnAddress(@RequestParam String address) {
        return safetyNetJsonService.getFireStationAndPeopleFromAddress(address);
    }

    @GetMapping("/flood/stations")
    public Map<String, Object> getHouseholdByStation(@RequestParam String stations) {
        return safetyNetJsonService.getHouseholdByStation(Arrays.asList(stations.split("-")));
    }

    @GetMapping("/personInfo")
    public List<Map<String, Object>> getPerson(@RequestParam String firstName, @RequestParam String lastName) {
        return safetyNetJsonService.getPersonByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailBasedOnCity(@RequestParam String city) {
        return safetyNetJsonService.allEmailFromCity(city);
    }
}
