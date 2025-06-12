package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.MedicalRecords;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecords, Integer> {
    Optional<MedicalRecords> findByCustomers(Customers customers);
}