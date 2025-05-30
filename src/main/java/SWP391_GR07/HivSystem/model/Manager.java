package SWP391_GR07.HivSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "office_phone", nullable = false)
    private String officePhone;

    public Manager() {
    }

    public Manager(User user, String department, String officePhone) {
        this.user = user;
        this.department = department;
        this.officePhone = officePhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
}
