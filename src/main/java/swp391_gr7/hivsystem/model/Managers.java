package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "managers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Managers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private int managerId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column(name = "office_phone", nullable = false)
    private String officePhone;


}
