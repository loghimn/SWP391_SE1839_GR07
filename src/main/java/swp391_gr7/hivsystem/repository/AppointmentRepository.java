package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Staffs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Integer> {
    @Override
    List<Appointments> findAll();

    List<Appointments> findAllByAnonymous(boolean anonymous);

    Appointments save(Appointments appointments);

    Optional<Appointments> findByAppointmentId(int appointmentId);

    List<Appointments> findByCustomers_CustomerId(int customerId);

    List<Appointments> findByDoctors_DoctorId(int doctorsDoctorId);

    List<Appointments> findByDoctors_DoctorIdAndAppointmentType(int doctorsDoctorId, String appointmentType);

    List<Appointments> findByDoctors_DoctorIdAndStartTimeBetween(int doctorsDoctorId, LocalDateTime startTimeAfter, LocalDateTime startTimeBefore);

    List<Appointments> findByAppointmentType(String appointmentType);

    boolean existsByStartTimeAndDoctors_DoctorIdAndStatus(LocalDateTime startTime, int doctorsDoctorId, boolean status);

    @Query("SELECT a FROM Appointments a WHERE a.doctors = :doctor AND a.startTime BETWEEN :start AND :end")
    List<Appointments> findAppointmentsByDoctorsAndDateRange(@Param("doctor") Doctors doctor,
                                                             @Param("start") LocalDateTime start,
                                                             @Param("end") LocalDateTime end);

    // In AppointmentRepository.java
    List<Appointments> findByDoctorsAndAppointmentTypeAndStatus(Doctors doctor, String appointmentType, boolean status);

    List<Appointments> findByStaffsAndAppointmentTypeAndStatus(Staffs staff, String appointmentType, boolean status);
}
