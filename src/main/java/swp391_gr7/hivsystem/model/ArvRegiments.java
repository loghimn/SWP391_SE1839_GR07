package swp391_gr7.hivsystem.model;

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

    @Column(name = "level")
    private int level;

    @Nationalized
    @Column(name = "description")
    private String description;

    // Optional: Liên kết ngược
    @OneToMany(mappedBy = "arvRegiment", cascade = CascadeType.ALL)
    private List<ArvMedications> medications = new ArrayList<>();

    public ArvRegiments(int level, String description) {
        this.level = level;
        this.description = description;
    }
}
