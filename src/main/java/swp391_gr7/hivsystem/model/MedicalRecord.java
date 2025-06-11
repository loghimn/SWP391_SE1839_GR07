package swp391_gr7.hivsystem.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;

    @Entity
    @Table(name = "medicalRecords")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class MedicalRecord {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int medicalRecordID;

        @ManyToOne
        @JoinColumn(name = "customerID", nullable = false)
        private Customer customer;

        @ManyToOne
        @JoinColumn(name = "doctorID", nullable = false)
        private Doctor doctor;

        @Column(name = "diagnosis", nullable = false)
        private String diagnosis;

        @Column(name = "treatment", nullable = false)
        private String treatment;

        @Column(name = "recordDate", nullable = false)
        private LocalDate recordDate;
    }