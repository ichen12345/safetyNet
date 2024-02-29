package com.openclassrooms.safetyNet.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String city;
    
    @Column
    private String zip;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToOne
    @JoinColumn(name = "medical_id", referencedColumnName = "id")
    private MedicalRecord medicalRecord;
  
}
