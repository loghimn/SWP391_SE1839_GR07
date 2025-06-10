package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends CrudRepository<Staff, Long> {
    @Query(
            value = "SELECT s.* FROM staff s JOIN users u ON s.user_id = u.user_id WHERE u.username = :StaffName",
            nativeQuery = true
    )
    Optional<Doctor> findDoctorByUser_Username(@Param("staffName") String staffName);
    @Query("SELECT s FROM Staff s")
    List<Staff> findAllStaff();
}
