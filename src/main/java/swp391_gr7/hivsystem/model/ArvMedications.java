package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arv_medications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArvMedications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arv_medication_id")
    private int arvMedicationID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnore
    private Doctors doctor;

    @Nationalized
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;

    @Nationalized
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Nationalized
    @Column(name = "form", length = 50, nullable = false)
    private String form;

    @Nationalized
    @Column(name = "strength", length = 20, nullable = false)
    private String strength;

    @Nationalized
    @Column(name = "manufacturer", length = 100, nullable = true)
    private String manufacturer;

    @Nationalized
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private ArvRegiments arvRegiment;

    public ArvMedications(Doctors doctor, String code, String name, String form, String strength, String manufacturer, String description, boolean isActive, ArvRegiments arvRegiment) {
        this.doctor = doctor;
        this.code = code;
        this.name = name;
        this.form = form;
        this.strength = strength;
        this.manufacturer = manufacturer;
        this.description = description;
        this.isActive = isActive;
        this.arvRegiment = arvRegiment;
    }
}