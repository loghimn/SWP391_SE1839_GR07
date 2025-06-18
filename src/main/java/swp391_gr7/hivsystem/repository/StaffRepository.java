package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Staffs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staffs, Integer> {
    @Query(
            value = "SELECT s.* FROM staff s JOIN users u ON s.user_id = u.user_id WHERE u.username = :staffName",
            nativeQuery = true
    )
    Optional<Doctors> findDoctorByUser_Username(@Param("staffName") String staffName);

    @Query("SELECT s FROM Staffs s")
    List<Staffs> findAllStaff();

    @Query(
            value = "SELECT s.* FROM staffs s JOIN users u ON s.user_id = u.user_id WHERE u.email = :mail",
            nativeQuery = true)
    Optional<Staffs> findStaffByMail(@Param("mail") String email);
}