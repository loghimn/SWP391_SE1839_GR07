package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT c.* FROM customer c JOIN users u ON c.user_id = u.user_id WHERE u.email = :mail", nativeQuery = true)
    Optional<Customer> findCustomersByMail(@Param("mail") String mail);

    // Optional<Customer> findByEmail(String email);

    @Query(value = "SELECT * FROM customer c JOIN users u ON c.user_id = u.user_id WHERE u.phone = :phone", nativeQuery = true)
    Optional<Customer> findCustomersByPhone(@Param("phone") String phone);
    }
