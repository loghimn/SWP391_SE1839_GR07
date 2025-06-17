package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Schedules;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {
    List<Schedules> findByDoctors_DoctorId(int doctorsDoctorId);
}