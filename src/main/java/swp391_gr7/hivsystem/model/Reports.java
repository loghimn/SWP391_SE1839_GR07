package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reports {
    /*reportID (PK)

    managerID : int (FK)

    reportType : varchar

    createdAt : LocalDateTime*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportID;
//
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
    @Column(name = "report_type")
    private String reportType;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
