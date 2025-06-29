package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    Customers findByUsers(Users user);

    Customers getCustomersByCustomerId(int customerId);

    Customers findByCustomerId(int customerId);
}
