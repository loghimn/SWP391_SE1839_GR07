package swp391_gr7.hivsystem.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.UserAndCustomerUpdateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndStaffUpdateRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.service.DoctorService;
import swp391_gr7.hivsystem.service.UserService;

import java.util.List;

@RestController
//CRUD
@RequestMapping("/api/user/manager")
@SecurityRequirement(name = "bearerAuth")
public class ManagerController {
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;



    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/list/active")
    public ApiResponse<List> DoctorsListActive() {
        List<Doctors> doctorsListActive = doctorService.showAllDoctorsActive();
        boolean result = doctorsListActive != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(doctorsListActive)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/list/full")
    public ApiResponse<List> DoctorsListFull() {
        List<Doctors> doctorsListFull = doctorService.showAllDoctors();
        boolean result = doctorsListFull != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(doctorsListFull)
                .message(resultString)
                .build();
    }


    @PreAuthorize("hasRole('Manager')")
    @PutMapping("/delete/{userId}")
    public Users deleteDoctorStaffCus(@PathVariable int userId) {
        return userService.deleteDoctorStaffCus(userId);
    }


    @PreAuthorize("hasRole('Manager')")
    @PutMapping("/update/staff/{staffId}")
    public boolean updateStaff(@PathVariable int staffId, @RequestBody @Valid UserAndStaffUpdateRequest request) {
        return userService.updateUserAndStaff(staffId, request);
    }

    @PreAuthorize("hasRole('Manager')")
    @PutMapping("/update/doctor/{doctorId}")
    public boolean updateDoctor(@PathVariable int doctorId, @RequestBody @Valid UserAndDoctorUpdateRequest request) {
        return userService.updateUserAndDoctor(doctorId, request);
    }

    @PreAuthorize("hasRole('Manager')")
    @PutMapping("/update/customer/{customerId}")
    public boolean updateCustomer(@PathVariable int customerId, @RequestBody @Valid UserAndCustomerUpdateRequest request) {
        return userService.updateUserAndCustomer(customerId, request);
    }
}
