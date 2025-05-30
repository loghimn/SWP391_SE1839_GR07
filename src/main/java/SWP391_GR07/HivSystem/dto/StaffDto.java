package SWP391_GR07.HivSystem.dto;

import java.time.LocalDate;

public class StaffDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String department;
    private int workShift;
    private String assignedModule;
    private String role = "staff";

    public StaffDto() {
    }

    public StaffDto(String username, String password, String email, String phone,
                    String fullName, LocalDate dateOfBirth, String gender,
                    String department, int workShift, String assignedModule) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.department = department;
        this.workShift = workShift;
        this.assignedModule = assignedModule;
        this.role = "staff";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
