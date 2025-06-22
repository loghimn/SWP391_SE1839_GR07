package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.List;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "test_result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private int testResultID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctors;

    @Column(name = "test_type" , nullable = false)
    private String testType;

    @Column(name = "result_value", nullable = false)
    private boolean resultValue;

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    @Nationalized
    @Column(name = "notes")
    private String notes;
    //
    @OneToOne
    @JoinColumn(name = "treatment_plan_id" , nullable = false)
    private TreatmentPlans treatmentPlan;
    //
    @Column(name = "re_examination" , nullable = false)
    private boolean re_examination;
    //
    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointments appointments;
    //
}