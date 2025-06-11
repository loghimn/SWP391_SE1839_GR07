package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// tu phat sinh id
    @Column(name = "user_id")
    private Integer userId;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = true)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String fullName;

    private LocalDate dateOfBirth;

    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

    }
    @Column(length = 10, nullable = false)
    private boolean status;
//
    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "customer_id")
    private Customer customer;
  //
  @OneToOne(fetch = FetchType.LAZY)

  @JoinColumn(name = "doctor_id")
  private Doctor doctor;
  //
  @OneToOne(fetch = FetchType.LAZY)

  @JoinColumn(name = "manager_id")
  private Manager manager;
  //
  @OneToOne(fetch = FetchType.LAZY)

  @JoinColumn(name = "staff_id")
  private Staff staff;
}
