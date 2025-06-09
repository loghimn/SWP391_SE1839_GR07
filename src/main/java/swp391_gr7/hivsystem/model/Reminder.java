package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reminderID; // tên biến đặt chuẩn camel case

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "reminderType", nullable = false)
    private String reminderType;

    @Column(name = "reminderTime", nullable = false)
    private LocalDateTime reminderTime;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
}
