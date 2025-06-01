package swp391_gr7.hivsystem.repository;

import swp391_gr7.hivsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
