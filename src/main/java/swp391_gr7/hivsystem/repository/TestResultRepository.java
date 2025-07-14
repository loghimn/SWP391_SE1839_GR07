package swp391_gr7.hivsystem.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.TestResults;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResults, Integer> {
    List<TestResults> findByCustomers_CustomerId(int customerId);

    TestResults save(TestResults testResults);

    Customers findByCustomersOrderByTestResultID(Customers customers);

    List<TestResults> findByDoctors_DoctorId(int doctorId);

    @Query(value = "SELECT * FROM test_result tr WHERE tr.test_result_id NOT IN (SELECT test_result_id FROM reminders)", nativeQuery = true)
    List<TestResults> findTestResultsNoHaveReminder();

    List<TestResults> findTestResultsByCustomers(Customers customers);
}
