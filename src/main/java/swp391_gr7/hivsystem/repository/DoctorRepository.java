package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import swp391_gr7.hivsystem.model.Staff;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Doctor findByLicenseNumber(String licenseNumber);

    @Query("SELECT d FROM Doctor d WHERE d.user.username = :username")
    Optional<Doctor> findDoctorByUsername(@Param("username") String username);

//Truy van join


}
