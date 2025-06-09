package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    @Query(
            value = "SELECT s.* FROM staffs s JOIN users u ON s.user_id = u.user_id WHERE u.email = :mail",
            nativeQuery = true)
    Optional<Staff> findStaffByMail(@Param("mail") String email);
}
