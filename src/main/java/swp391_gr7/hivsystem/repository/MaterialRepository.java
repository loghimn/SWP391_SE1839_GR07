package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
