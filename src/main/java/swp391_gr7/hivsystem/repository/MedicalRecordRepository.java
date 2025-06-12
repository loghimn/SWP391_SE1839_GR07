package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.MedicalRecords;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecords, Integer> {
}