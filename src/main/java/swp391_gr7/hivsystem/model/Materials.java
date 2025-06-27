package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Entity
@Table(name = "materials")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Materials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int materialID;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnore
    private Doctors doctor;

    @Nationalized
    @Column(name = "title", nullable = false)
    private String title;

    @Nationalized
    @Lob // lưu những cái text lớn
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Nationalized
    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

}
