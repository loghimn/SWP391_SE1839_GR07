package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MedicalRecords {
  /*  medicalRecordID (PK)

    customerID : int (FK)

    appointmentID : int (FK)

    diagnosis : varchar

    treatment : varchar

    recordDate : LocalDate*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id")
    private Long medicalRecordID;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "treatment")
    private String treatment;
    @Column(name = "record_date")
    private LocalDate recordDate;
    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "customer_id")
    private Customer customer;
}
