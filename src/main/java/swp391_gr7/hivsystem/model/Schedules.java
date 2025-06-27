package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Schedules {
    // scheduleID (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int scheduleID;

    //doctorID : int (FK)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctors;

    //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "managerID", nullable = false)
    private Managers managers;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Nationalized
    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "schedules")
    @JsonIgnore
    private List<Appointments> appointments;

}
