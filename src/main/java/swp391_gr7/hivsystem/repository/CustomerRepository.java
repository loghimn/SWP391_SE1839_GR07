package swp391_gr7.hivsystem.repository;

import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    }
