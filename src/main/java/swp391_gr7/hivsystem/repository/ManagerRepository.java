package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Managers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManagerRepository extends CrudRepository<Managers, Integer> {
    // Add custom query methods here if needed
    @Query(value = "SELECT * FROM managers WHERE manager_id = 1",
            nativeQuery = true)
    Managers findManagerById(int id);

    @Query(
            value = "SELECT m.* FROM managers m JOIN users u ON m.user_id = u.user_id WHERE u.email = :mail",
            nativeQuery = true)
    Optional<Managers> findManagerByMail(@Param("mail") String email);
}