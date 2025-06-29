package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.TreatmentPlans;

import java.util.List;

@Repository

public interface TreatmentPlansRepository extends JpaRepository<TreatmentPlans, Integer> {

    TreatmentPlans getTreatmentPlansByTreatmentPlanID(int treatmentPlanID);

    List<TreatmentPlans> findAllByDoctors_DoctorId(int doctorId);

    List<TreatmentPlans> findAllByAppointments_AppointmentId(int appointmentId);
}
