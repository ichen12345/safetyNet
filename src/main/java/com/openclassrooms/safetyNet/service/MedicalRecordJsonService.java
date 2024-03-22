package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordJsonService {
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> getMedicalRecords();
}
