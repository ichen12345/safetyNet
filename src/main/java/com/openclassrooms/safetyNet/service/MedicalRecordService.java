package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.entity.MedicalRecord;

public interface MedicalRecordService {
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(Integer id);

}
