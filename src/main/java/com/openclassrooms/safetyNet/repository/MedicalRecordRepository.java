package com.openclassrooms.safetyNet.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.safetyNet.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{
    LocalDate findByBirthDate(LocalDate birthDate);
}
