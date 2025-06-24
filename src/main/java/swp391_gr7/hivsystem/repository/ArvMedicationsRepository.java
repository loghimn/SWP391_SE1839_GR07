package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.ArvMedications;

import java.util.List;

@Repository
public interface ArvMedicationsRepository extends JpaRepository<ArvMedications, Integer> {
    //List<ArvMedications> findByDescriptionContainingIgnoreCaseAndArvRegiment_IdAndIsActiveTrue(String description, int arvRegimentId);

    List<ArvMedications> findAllByDescriptionContainingIgnoreCase(String keyword);

    ArvMedications getArvMedicationsByCode(String code);


}
