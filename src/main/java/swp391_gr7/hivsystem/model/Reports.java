package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reports {
    /*reportID (PK)

    managerID : int (FK)

    reportType : varchar

    createdAt : LocalDateTime*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportID;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Managers managers;

    @Nationalized
    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
