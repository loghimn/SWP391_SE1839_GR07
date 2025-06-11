package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;
//
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
//
    @ManyToOne
    @JoinColumn(name = "doctor_id ", nullable = false)
    private Doctor doctor;
//
    @ManyToOne
    @JoinColumn(name = "staff_id ", nullable = false)
    private Staff staff;

    @Column(name = "appointment_time")
    private LocalDate appointmentTime;  // bỏ length, LocalDate không cần length

    @Column(name = "status")
    private boolean status;  // boolean không cần length

    @Column(name = "anonymous")
    private boolean anonymous;  // boolean không cần length

    @Column(name = "appointment_type")
    private String appointmentType;
//
    @ManyToOne
    @JoinColumn(name = "schedule_id ", nullable = false)
    private Schedules schedule;
//Map 1 n voi testResults
    @OneToOne(mappedBy = "appointment")
    @JsonIgnore
    private TestResults testResults;

//Map 1 1 voi consultation
    @OneToOne(mappedBy = "appointment")
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultations consultation;
}

