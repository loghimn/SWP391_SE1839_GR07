package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staffId;
//
    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "work_shift", nullable = false)
    private int workShift;

    @Column(name = "assigned_area", nullable = false)
    private String assignedArea;
//
    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Appointment> appointments;

    //
    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Reminder> reminders;
//
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
}
