package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;
    //
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customers;
    //
    @ManyToOne
    @JoinColumn(name = "doctor_id ", nullable = false)
    private Doctors doctors;
    //
    @ManyToOne
    @JoinColumn(name = "staff_id ", nullable = false)
    private Staffs staffs;

    @ManyToOne
    @JoinColumn(name = "medical_record_id ", nullable = false)
    private MedicalRecords medicalRecords;

    @Column(name = "appointment_time", nullable = false)
    private LocalDate appointmentTime;  // bỏ length, LocalDate không cần length

    @Column(name = "status", nullable = false)
    private boolean status;  // boolean không cần length

    @Column(name = "anonymous", nullable = false)
    private boolean anonymous;  // boolean không cần length

    @Nationalized
    @Column(name = "appointment_type", nullable = false)
    private String appointmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedules schedules;


}

