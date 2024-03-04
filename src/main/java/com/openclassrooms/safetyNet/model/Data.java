package com.openclassrooms.safetyNet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@lombok.Data
public class Data {
    private List<Person> persons;

    private List<FireStation> firestations;

    private List<MedicalRecord> medicalrecords;
}
