package com.openclassrooms.safetyNet.Service;

import java.util.List;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Model.MedicalRecord;
import com.openclassrooms.safetyNet.Model.Person;

public interface SafetyNetService {

    List<Person> personServicedFirestation(String station);

    List<Person> childrenFromAddressAlert(String address);

    List<String> allEmailFromCity(String city);

    List<Person> getHouseholdByStation(List<FireStation> stations);

    List<Person> getPersonByName(String firstName, String lastName);

    List<Person> getPersonAndStationFromAddress(String address);
}
