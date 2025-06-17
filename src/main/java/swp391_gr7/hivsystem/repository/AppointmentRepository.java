package swp391_gr7.hivsystem.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import swp391_gr7.hivsystem.model.Appointments;

    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

    @Repository
    public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
        @Override
        List<Appointments> findAll();
        List<Appointments> findAllByAnonymous(boolean anonymous);
        Appointments save(Appointments appointments);
        Optional<Appointments> findByAppointmentTime(Date appointmentTime);
        List<Appointments> findAllByDoctors_DoctorId(Long doctorId); // Fixed method name
    }