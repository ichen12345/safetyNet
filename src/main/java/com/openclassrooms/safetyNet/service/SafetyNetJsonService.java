package com.openclassrooms.safetyNet.service;

import java.util.List;
import java.util.Map;

public interface SafetyNetJsonService {
    Map<String, Object> personServicedFireStation(String station);
    Map<String, Object> childrenFromAddress(String address);
    List<String> getPersonPhoneFromStation(String station);
    Map<String, Object> getFireStationAndPeopleFromAddress(String address);
    List<Map<String, Object>> getPersonByName(String firstName, String lastName);
    Map<String, Object> getHouseholdByStation(List<String> stations);
    List<String> allEmailFromCity(String city);

}
