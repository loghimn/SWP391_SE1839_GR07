package swp391_gr7.hivsystem.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
