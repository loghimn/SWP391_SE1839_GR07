package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.TestResults;

@Entity
@Table(name = "treatment_plan")
public class TreatmentPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_plan_id")
    private Long treatmentPlanID;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
//
    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "test_result_id", unique = true)
    private TestResults testResult;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "arvRegimentID", nullable = false)
    private ArvRegiments arvRegiment;

    private String planDescription;
}