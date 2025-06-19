package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int arvRegimentID;;
    @Column(name = "level")
    private int level; // 1: bậc 1, 2: bậc 2
    @Column(name = "is_for_pregnancy")
    private boolean isForPregnancy;
    @Column(name = "description", length = 1000)
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_result_id")
    private TestResults testResults;

}
