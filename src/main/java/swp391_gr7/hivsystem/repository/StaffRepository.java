package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Staffs;
import org.springframework.data.repository.CrudRepository;
import swp391_gr7.hivsystem.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staffs, Integer> {
    @Query("SELECT s FROM Staffs s")
    List<Staffs> findAllStaff();

    Staffs findByUsers(Users user);

    //Tim staff by user id
    @Query(
            value = "SELECT s.* FROM staffs s JOIN users u ON s.user_id = u.user_id WHERE u.user_id = :userId",
            nativeQuery = true
    )
    Optional<Staffs> findStaffByUser_UserId(@Param("userId") String userId);
}