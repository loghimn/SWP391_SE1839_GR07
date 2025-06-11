package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private int doctorId;
//
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "year_experience", nullable = false)
    private int yearExperience;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;
//
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;
//
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Schedules> schedules ;
//
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
//
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<TreatmentPlans> treatmentPlans;
    //
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Consultations> consultations;
//
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<TestResults> testResults;
}
