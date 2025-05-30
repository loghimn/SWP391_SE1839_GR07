package SWP391_GR07.HivSystem.repository;

import SWP391_GR07.HivSystem.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Doctor findByLicenseNumber(String licenseNumber);
}
