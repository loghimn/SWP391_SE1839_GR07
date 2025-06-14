package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.Consultations;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultations, Long> {
    List<Consultations> findByCustomers_CustomerId(Long customerId);
    List<Consultations> findByDoctors_DoctorId(Long doctorId);
    List<Consultations> findByAppointments_AppointmentId(Long appointmentId);
}