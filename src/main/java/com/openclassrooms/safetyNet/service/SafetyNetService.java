package com.openclassrooms.safetyNet.service;

import java.util.List;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;

public interface SafetyNetService {

    List<Person> personServicedFireStation(String station);

    List<Person> childrenFromAddressAlert(String address);

    List<String> allEmailFromCity(String city);

    List<Person> getHouseholdByStation(List<FireStation> stations);

    List<Person> getPersonByName(String firstName, String lastName);

    List<Person> getPersonAndStationFromAddress(String address);
}
