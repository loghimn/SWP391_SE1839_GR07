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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int doctorId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Nationalized
    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "year_experience", nullable = false)
    private int yearExperience;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Managers managers;

    @OneToMany(mappedBy = "doctors", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Appointments> appointments;
}