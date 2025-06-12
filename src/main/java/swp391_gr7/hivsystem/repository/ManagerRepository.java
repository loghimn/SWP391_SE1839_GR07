package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import swp391_gr7.hivsystem.model.Managers;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Managers, Integer> {
    // Add custom query methods here if needed
    @Query(value = "SELECT * FROM managers WHERE manager_id = 1",
            nativeQuery = true)
    Managers findManagerById(int id);
}