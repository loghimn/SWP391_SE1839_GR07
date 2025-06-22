package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arv_medications")
public class ArvMedications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arv_medication_id")
    private int arvMedicationID;
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;  // Mã thuốc ngắn, ví dụ: TDF, 3TC

    @Column(name = "name", nullable = false, length = 100)
    private String name;  // Tên đầy đủ: Tenofovir disoproxil fumarate

    @Column(name = "form", length = 50, nullable = false)
    private String form;  // Dạng bào chế: viên nén, siro

    @Column(name = "strength", length = 20, nullable = false)
    private String strength;  // Hàm lượng: 300mg, 50mg...

    @Column(name = "manufacturer", length = 100, nullable = true)
    private String manufacturer;  // hãng sản xuất

    @Nationalized
    @Column(name = "description", length = 1000)
    private String description;  // mô tả mở rộng

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne
    @JsonIgnore
    private ArvRegiments arvRegimens;


}
