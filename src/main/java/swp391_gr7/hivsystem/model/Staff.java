package swp391_gr7.hivsystem.model;

import jakarta.persistence.*;


@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "work_shift", nullable = false)
    private int workShift;

    @Column(name = "assigned_module", nullable = false)
    private String assignedModule;

    public Staff(){
    }

    public Staff(User user, String department, int workShift, String assignedModule) {
        this.user = user;
        this.department = department;
        this.workShift = workShift;
        this.assignedModule = assignedModule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getWorkShift() {
        return workShift;
    }

    public void setWorkShift(int workShift) {
        this.workShift = workShift;
    }

    public String getAssignedModule() {
        return assignedModule;
    }

    public void setAssignedModule(String assignedModule) {
        this.assignedModule = assignedModule;
    }
}
