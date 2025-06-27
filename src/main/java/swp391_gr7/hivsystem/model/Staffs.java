package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "staffs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Staffs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staffId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Nationalized
    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "work_shift", nullable = false)
    private int workShift;

    @Nationalized
    @Column(name = "assigned_area", nullable = false)
    private String assignedArea;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", nullable = false)
    private Managers managers;

    @OneToMany(mappedBy = "staffs", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointments> appointments;
}