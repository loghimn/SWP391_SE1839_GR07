package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;

    @Nationalized
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "customers")
    @JsonIgnore
    private List<Appointments> appointments;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Managers managers;

    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TestResults> testResultsList;
}