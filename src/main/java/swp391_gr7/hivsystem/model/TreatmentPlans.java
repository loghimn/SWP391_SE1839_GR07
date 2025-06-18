package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment_plan")
public class TreatmentPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_plan_id")
    private int treatmentPlanID;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctors;

    @Column(name = "plan_description", nullable = false)
    private String planDescription;

    @Column(name = "dosage_time") // thời gian uống thuốc
    private LocalTime dosageTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arv_reqiment_id", nullable = false)
    private ArvRegiments arvReqimentID ;

}