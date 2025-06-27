package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "arv_regiments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArvRegiments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arv_regiment_id")
    private int arvRegimentID;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "level", nullable = false)
    private int level;

    @Nationalized
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnore
    private Doctors doctor;

    // Optional: Liên kết ngược
    @OneToMany(mappedBy = "arvRegiment", cascade = CascadeType.ALL)
    private List<ArvMedications> medications = new ArrayList<>();


    public ArvRegiments(String name, Doctors doctor, String description, int level) {
        this.name = name;
        this.doctor = doctor;
        this.description = description;
        this.level = level;
    }
}
