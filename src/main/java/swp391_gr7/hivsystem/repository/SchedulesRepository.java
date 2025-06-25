package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Schedules;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {
    List<Schedules> findByDoctors_DoctorId(int doctorId);

    List<Schedules> findByDoctors(Doctors doctors);
}