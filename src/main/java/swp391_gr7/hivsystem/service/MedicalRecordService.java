package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.model.MedicalRecord;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    List<MedicalRecord> getAll();
    Optional<MedicalRecord> getById(int id);
    MedicalRecord addMedicalRecord(MedicalRecordCreateRequest request);
    void deleteById(int id);

}