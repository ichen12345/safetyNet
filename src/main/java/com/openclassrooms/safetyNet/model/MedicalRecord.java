package com.openclassrooms.safetyNet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.safetyNet.entity.Person;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class MedicalRecord {

    private String firstName;

    private String lastName;

    private String birthdate;

    private List<String> medications;

    private List<String> allergies;

}
