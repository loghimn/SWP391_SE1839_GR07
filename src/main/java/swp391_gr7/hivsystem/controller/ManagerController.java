package swp391_gr7.hivsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiResponse;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.service.DoctorService;
import swp391_gr7.hivsystem.service.UserService;

import java.util.List;

@RestController
//CRUD
@RequestMapping("/api/user/manager")
public class ManagerController {
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;


    @PutMapping("/update/{userId}")
    public boolean updateDoctor(@PathVariable int userId, @RequestBody UserAndDoctorUpdateRequest request) {
        return userService.updateUserAndDoctor(userId, request);
    }
    @GetMapping("/list/active")
    public ApiResponse<List> DoctorsListActive() {
        List <Doctors> doctorsListActive = doctorService.showAllDoctorsActive();
        boolean result = doctorsListActive != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(doctorsListActive)
                .message(resultString)
                .build();
    }
    @GetMapping("/list/full")
    public ApiResponse<List> DoctorsListFull() {
        List <Doctors> doctorsListFull = doctorService.showAllDoctors();
        boolean result = doctorsListFull != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(doctorsListFull)
                .message(resultString)
                .build();
    }

    @PutMapping("/delete/{userId}")
    public Users deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
