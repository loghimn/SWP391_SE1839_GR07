package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
//
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address", nullable = false)
    private String address;
//
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Appointment> appointments;
//
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
//
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<TestResults> testResults;
//
    @OneToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecords medicalRecords;
//
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Reminder> reminders;
//
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Consultations> consultations;
}
