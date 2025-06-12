package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedules {
   // scheduleID (PK)
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "schedule_id")
   private int scheduleID;

    //doctorID : int (FK)
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "doctor_id ", nullable = false)
    private Doctors doctors;

    //
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "managerID", nullable = false)
    private Managers managers;
    @Column(name = "work_date")
    private LocalDate workDate;
    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "schedules")
    @JsonIgnore
    private List<Appointments> appointments;

}
