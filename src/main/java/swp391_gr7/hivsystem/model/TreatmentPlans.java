package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TreatmentPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_plan_id")
    private int treatmentPlanID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointments appointments;

    @Nationalized
    @Column(name = "plan_description")
    private String planDescription;

    @Column(name = "dosage_time") // thời gian uống thuốc
    private LocalTime dosageTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arv_regiment_id", nullable = false)
    private ArvRegiments arvReqimentID;

}