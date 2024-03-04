package com.openclassrooms.safetyNet.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecord implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate birthDate;

    @Column
    private List<String> medications;

    @Column
    private List<String> allergies;

    @OneToOne(mappedBy = "medicalRecord")
    private Person person;

}
