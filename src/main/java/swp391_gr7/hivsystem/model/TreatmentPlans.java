package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "treatment_plan")
public class TreatmentPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_plan_id")
    private Long treatmentPlanID;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctors;

    @Column(name = "plan_description", nullable = false)
    private String planDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arv_reqiment_id", nullable = false)
    private ArvRegiments arvReqimentID ;

}