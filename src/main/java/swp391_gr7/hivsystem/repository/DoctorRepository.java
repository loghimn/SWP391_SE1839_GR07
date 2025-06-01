package swp391_gr7.hivsystem.repository;

import swp391_gr7.hivsystem.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Doctor findByLicenseNumber(String licenseNumber);
}
