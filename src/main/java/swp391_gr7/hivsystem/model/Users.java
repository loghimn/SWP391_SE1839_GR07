package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// tu phat sinh id
    @Column(name = "user_id")
    private Integer userId;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = true)
    private String password;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    private String phone;

    @Nationalized
    @Column(length = 100 , nullable = false)
    private String fullName;

    @Column(length = 255 , nullable = true)
    private LocalDate dateOfBirth;

    @Column(length = 10, nullable = true)
    private String gender;

    @Column(length = 20)
    private String role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

    }
    @Column(nullable = false)
    private boolean status;

}
