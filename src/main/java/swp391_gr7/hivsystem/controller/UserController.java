package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.service.AppointmentService;
import swp391_gr7.hivsystem.service.UserService;



@RestController
//CRUD
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //Create user
    //http://localhost:8080/user/create
    @PostMapping("/customer/register")
    public ApiReponse<Boolean> registerUserAndCustomer(@RequestBody UserAndCustomerCreateRequest request) {
        boolean result = userService.registerUserAndCustomer(request); // gọi từ service
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }
    @PostMapping("/doctor/register")
    public ApiReponse<Boolean> registerUserAndDoctor(@RequestBody UserAndDoctorCreateRequest request) {
        boolean result = userService.registerUserAndDoctor(request); // gọi từ service
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }
    @PostMapping("/manager/register")
    public ApiReponse<Boolean> registerUserAndManager(@RequestBody UserAndManagerCreateRequest request) {
        boolean result = userService.registerUserAndManager(request); // gọi từ service
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }
    @PostMapping("/staff/register")
    public ApiReponse<Boolean> registerUserAndStaff(@RequestBody UserAndStaffCreateRequest request) {
        boolean result = userService.registerUserAndStaff(request); // gọi từ service
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    //Get user by id
    //http://localhost:8080/user/userId  id tu phat sinh
    @GetMapping("/{userId}")
        public User getUser(@PathVariable int userId) {
        return userService.findUserByUserId(userId);
    }
    //Update user by user id
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updtae/{userId}")
        public User updateUser(@PathVariable int userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    //Delete user by id
    /*
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
    */
    @Autowired
    public AppointmentService appointmentService;

    @PostMapping("/appoint/register")
    public ApiReponse<Boolean> appointmentRequest(@RequestBody AppointmentCreateRequest request) {
        Appointment appointment = appointmentService.addAppointment(request);// gọi từ service
        boolean result = appointment != null;
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }
}

