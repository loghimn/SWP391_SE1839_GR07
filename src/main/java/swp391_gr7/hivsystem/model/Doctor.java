package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int DoctorId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "year_experience", nullable = false)
    private int yearExperience;

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @OneToMany
    @JoinColumn(name = "appointment_id")
    private List<Appointment> appointment;
}
