package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminID;
//Noi 1 voi 1 bang user
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "assigned_area", nullable = false)
    private String assignedArea;

    //Noi 1 nhieu voi bolg
    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Blog> blogs;

    //Noi 1 nhieu voi meterial
    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Material> materials;
}
