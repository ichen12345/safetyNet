package com.openclassrooms.safetyNet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.safetyNet.Model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{
    
}
