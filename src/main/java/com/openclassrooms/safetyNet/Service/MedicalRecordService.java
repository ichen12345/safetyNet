package com.openclassrooms.safetyNet.Service;

import com.openclassrooms.safetyNet.Model.MedicalRecord;

public interface MedicalRecordService {
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(Integer id);

}
