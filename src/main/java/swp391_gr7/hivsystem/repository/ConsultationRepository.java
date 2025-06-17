package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.Consultations;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultations, Integer> {
    List<Consultations> findByCustomers_CustomerId(int customerId);
    List<Consultations> findByDoctors_DoctorId(int doctorId);
    List<Consultations> findByAppointments_AppointmentId(int appointmentId);
}