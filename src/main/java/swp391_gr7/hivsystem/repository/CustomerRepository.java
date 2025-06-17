package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    @Query(
            value = "SELECT c.* FROM customers c JOIN users u ON c.user_id = u.user_id WHERE u.email = :mail",
            nativeQuery = true)
    Optional<Customers> findCustomersByMail(@Param("mail") String mail);

    // Optional<Customer> findByEmail(String email);
    @Query(
            value = "SELECT * FROM customers c JOIN users u ON c.user_id = u.user_id WHERE u.phone = :phone",
            nativeQuery = true)
    Optional<Customers> findCustomersByPhone(@Param("phone") String phone);

    Customers getCustomersByCustomerId(int customerId);



}
