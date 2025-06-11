package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;


    /*consultationID (PK)

    appointmentID : int (FK)

    doctorID : int (FK)

    customerID : int (FK)

    consultationDate : LocalDate

    notes : varchar*/

    @Entity
    @Table(name = "consultations")
    public class Consultations {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "consultation_id")
        private Long consultationID;
//
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "appointment_id", nullable = false)
        private Appointment appointment;
//
        @ManyToOne(fetch = FetchType.LAZY)

        @JoinColumn(name = "doctor_id", nullable = false)
        private Doctor doctor;
//
        @ManyToOne(fetch = FetchType.LAZY)

        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;
        @Column(name = "consultation_date")
        private LocalDate consultationDate;
        @Column(name = "notes")
        private String notes;
}
