package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminID;

    //Noi 1 voi 1 bang user
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Nationalized
    @Column(name = "assigned_area", nullable = false)
    private String assignedArea;


}
