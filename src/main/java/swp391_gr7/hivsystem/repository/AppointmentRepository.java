package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
