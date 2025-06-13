package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.TestResults;
import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResults, Long> {
    List<TestResults> findByCustomers_CustomerId(Long customerId);
    List<TestResults> findByAppointments_AppointmentId(Long appointmentId);
}