package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import swp391_gr7.hivsystem.model.Staff;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Doctor findByLicenseNumber(String licenseNumber);
    @Query(
            value = "SELECT d.* FROM doctors d JOIN users u ON d.user_id = u.user_id WHERE u.username = :doctorsName",
            nativeQuery = true
    )
    Optional<Doctor> findDoctorByUser_Username(@Param("doctorsName") String doctorsName);


//Truy van join


}
