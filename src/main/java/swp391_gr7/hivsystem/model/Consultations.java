package swp391_gr7.hivsystem.model;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import java.time.LocalDate;

    @Entity
    @Table(name = "consultations")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class Consultations {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "consultation_id")
        private Long consultationID;

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "appointment_id", nullable = false)
        private Appointments appointments;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "doctor_id", nullable = false)
        private Doctors doctors;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "customer_id", nullable = false)
        private Customers customers;

        @Column(name = "consultation_date")
        private LocalDate consultationDate;

        @Column(name = "notes")
        private String notes;
    }