package swp391_gr7.hivsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "manager_id")
    private int managerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "office_phone", nullable = false)
    private String officePhone;
//
    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Customer> customers;
//
    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Schedules> schedules ;
//
    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Doctor> doctors ;

    //
    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private User users ;
    //
    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Staff> staffs;

    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Reports> reports;


}
