package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUserId(int id);

    Optional<Users> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    Optional<Users> findByEmail(String email);

    @Query(
            "select month(u.createdAt) as month, " +
                    "year(u.createdAt) as year, " +
                    "u.role as role, " +
                    "count(u) as total " +
                    "from Users u " +
                    "where u.role IN ('Doctor', 'Staff', 'Customer') " +
                    "GROUP BY YEAR(u.createdAt), MONTH(u.createdAt), u.role " +
                    "ORDER BY YEAR(u.createdAt), MONTH(u.createdAt)")
    List<Object[]> countUsersByMonth();
}
