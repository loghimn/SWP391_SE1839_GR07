package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Doctors;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctors, Long> {
    Doctors findByLicenseNumber(String licenseNumber);

    @Query(
            value = "SELECT d.* FROM doctors d JOIN users u ON d.user_id = u.user_id WHERE u.username = :doctorsName",
            nativeQuery = true
    )
    Optional<Doctors> findDoctorByUser_Username(@Param("doctorsName") String doctorsName);

    // Lay toan bo doctor
    @Query("SELECT d FROM Doctors d")
    List<Doctors> findAllDoctors();

    //lay toan bo doctor con hoat dong
    @Query(
            value = "SELECT d.* FROM doctors d JOIN users u ON d.user_id = u.user_id WHERE u.status = 1",
            nativeQuery = true
    )
    List <Doctors> findAllDoctorActive();
//Tim doctor by user id
    @Query(
            value = "SELECT d.* FROM doctors d JOIN users u ON d.user_id = u.user_id WHERE u.user_id = :userId",
            nativeQuery = true
    )
    Optional<Doctors> findDoctorByUser_UserId(@Param("userId") String userId);

    @Query(value = "SELECT d.* FROM doctors d JOIN users u ON d.user_id = u.user_id WHERE u.email = :mail",
            nativeQuery = true)
    Optional<Doctors> findDoctorByMail(@Param("mail") String mail);

    boolean existsByLicenseNumber(String licenseNumber);
}
