package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arv_regiments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArvRegiments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arv_regiment_id")
    private Long arvRegimentID;
    @Column(name = "name_arv")
    private String nameARV;
    @Column(name = "description_arv")
    private String descriptionARV;
    @Column(name = "group_name") // "group" là từ khóa trong SQL, nên nên đổi tên cột
    private String group;
    @Column(name = "default_dosage")
    private String defaultDosage;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctors doctors;
}
