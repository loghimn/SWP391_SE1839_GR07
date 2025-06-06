package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.Doctor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Override
    List<Appointment> findAll();
    List<Appointment> findAllByAnonymous(boolean anonymous);
    Appointment save(Appointment appointment);
    Optional<Appointment> findByAppointmentTime(Date appointmentTime);

}
