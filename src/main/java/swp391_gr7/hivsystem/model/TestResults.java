package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "test_result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private Long testResultID;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctors;
    @Column(name = "test_type")
    private String testType;
    @Column(name = "result_value")
    private boolean resultValue;
    @Column(name = "test_date")
    private LocalDate testDate;
    @Column(name = "notes")
    private String notes;
    //
    @OneToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlans treatmentPlan;
    //
    @Column(name = "re_examination")
    private boolean re_examination;
    //
    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointments appointments;
    //
}