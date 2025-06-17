package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.TreatmentPlans;
@Repository

public interface TreatmentPlansRepository extends JpaRepository<TreatmentPlans, Integer>{

    TreatmentPlans getTreatmentPlansByTreatmentPlanID(int treatmentPlanID);
}
