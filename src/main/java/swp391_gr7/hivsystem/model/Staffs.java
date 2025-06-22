package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staffs")
public class Staffs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staffId;

    @OneToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users users;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "work_shift", nullable = false)
    private int workShift;

    @Column(name = "assigned_area", nullable = false)
    private String assignedArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Managers managers;

    @OneToMany(mappedBy = "staffs", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointments> appointments;
}