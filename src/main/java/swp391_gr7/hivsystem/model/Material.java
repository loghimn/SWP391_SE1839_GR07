package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "materials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int materialID;
//
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "createAt", nullable = false)
    private LocalDate createAt;

}
