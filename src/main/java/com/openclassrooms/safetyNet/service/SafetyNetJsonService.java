package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.Person;

import java.util.List;
import java.util.Map;

public interface SafetyNetJsonService {
    Map<String, Object> personServicedFireStation(String station);

    Map<String, Object> childrenFromAddress(String address);
}
