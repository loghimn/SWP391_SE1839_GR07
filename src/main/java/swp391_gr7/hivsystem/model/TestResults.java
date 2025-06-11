package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "test_result")
public class TestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private Long testResultID;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private String testType;

    private String resultValue;

    private LocalDate testDate;

    private String notes;
    //
    @OneToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlans treatmentPlan;
    //
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "cutomer_id")
    private Customer cutomer;

    private boolean reExamination;
    //
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    //
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}