package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Admin;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query(value = "SELECT a.* FROM admins a JOIN users u ON a.user_id = u.user_id WHERE u.email = :mail",
    nativeQuery = true)
    Optional<Admin> findAdminByMail(@Param("mail") String mail);
}
