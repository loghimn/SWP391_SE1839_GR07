package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "reminders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reminders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private int reminderID; // tên biến đặt chuẩn camel case
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customers;

    @Column(name = "reminderType", nullable = false)
    private String reminderType;

    @Column(name = "reminderTime", nullable = false)
    private LocalDateTime reminderTime;

    @Nationalized
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "status", nullable = false)
    private boolean status;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staffs staffs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_result_id", nullable = false)
    private TestResults testResults;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointments appointments;

}
