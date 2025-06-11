package swp391_gr7.hivsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.User;
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
    public ApiReponse<List> DoctorsListActive() {
        List <Doctor> DoctorListActive = doctorService.showAllDoctorsActive();
        boolean result = DoctorListActive != null;
        String resultString = result ? "Success" : "Failed";
        return ApiReponse.<List>builder()
                .result(DoctorListActive)
                .message(resultString)
                .build();
    }
    @GetMapping("/list/full")
    public ApiReponse<List> DoctorsListFull() {
        List <Doctor> DoctorListFull = doctorService.showAllDoctors();
        boolean result = DoctorListFull!= null;
        String resultString = result ? "Success" : "Failed";
        return ApiReponse.<List>builder()
                .result(DoctorListFull)
                .message(resultString)
                .build();
    }

    @PutMapping("/delete/{userId}")
    public User deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
