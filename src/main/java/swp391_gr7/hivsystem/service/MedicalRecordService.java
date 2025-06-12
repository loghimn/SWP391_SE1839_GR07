package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.model.MedicalRecords;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    List<MedicalRecords> getAll();
    Optional<MedicalRecords> getById(int id);
    MedicalRecords addMedicalRecord(MedicalRecordCreateRequest request);
    MedicalRecords updateMedicalRecord(int id, MedicalRecordCreateRequest request);
    void deleteById(int id);
    String getError();
}