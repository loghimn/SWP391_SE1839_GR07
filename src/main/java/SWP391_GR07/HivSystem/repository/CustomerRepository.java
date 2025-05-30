package SWP391_GR07.HivSystem.repository;

import SWP391_GR07.HivSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
