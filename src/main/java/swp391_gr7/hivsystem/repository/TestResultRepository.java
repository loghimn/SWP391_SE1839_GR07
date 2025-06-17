package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.TestResults;

import java.util.List;
@Repository
public interface TestResultRepository extends JpaRepository<TestResults, Long> {
    List<TestResults> findByCustomers_CustomerId(Long customerId);

    List<TestResults> findByAppointments_AppointmentId(Long appointmentId);

    TestResults save(TestResults testResults);

    Customers findByCustomersOrderByTestResultID(Customers customers);

    TestResults findTestResultsByTestResultID(int testResultID);
}
