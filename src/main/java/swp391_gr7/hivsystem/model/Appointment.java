package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;  // đổi tên biến thành appointmentId chuẩn camelCase

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "doctor_id ", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "staff_id ", nullable = false)
    private Staff staff;

    private LocalDate appointmentTime;  // bỏ length, LocalDate không cần length

    private boolean status;  // boolean không cần length

    private boolean anonymous;  // boolean không cần length
}

