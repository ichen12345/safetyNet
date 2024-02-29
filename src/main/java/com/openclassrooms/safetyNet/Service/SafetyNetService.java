package com.openclassrooms.safetyNet.Service;

import java.util.List;

import com.openclassrooms.safetyNet.Model.FireStation;
import com.openclassrooms.safetyNet.Model.MedicalRecord;
import com.openclassrooms.safetyNet.Model.Person;

public interface SafetyNetService {

    List<Person> personServicedFirestation(String station);

    // List<Children> childrenFromAddress(Person person, MedicalRecord medicalRecord);


}
