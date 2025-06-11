package swp391_gr7.hivsystem.controller;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.reponse.AuthenticationReponse;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.UserRepository;
import swp391_gr7.hivsystem.service.AuthenticationService;
import swp391_gr7.hivsystem.service.UserService;

import java.time.LocalDate;


@RestController
//CRUD
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;

    //Create user
    //http://localhost:8080/user/create
    @PostMapping("/customer/register")
    public ApiReponse<Boolean> registerUserAndCustomer(@RequestBody UserAndCustomerCreateRequest request) {
        boolean result = userService.registerUserAndCustomer(request);
        return ApiReponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }


    @PostMapping("/doctor/register")
    public ApiReponse<Boolean> registerUserAndDoctor(@RequestBody UserAndDoctorCreateRequest request) {
        var result = userService.registerUserAndDoctor(request);
        return ApiReponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/manager/register")
    public ApiReponse<Boolean> registerUserAndManager(@RequestBody UserAndManagerCreateRequest request) {
        var result = userService.registerUserAndManager(request);
        return ApiReponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/staff/register")
    public ApiReponse<Boolean> registerUserAndStaff(@RequestBody UserAndStaffCreateRequest request) {
        var result = userService.registerUserAndStaff(request);
        return ApiReponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
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
    @PutMapping("/update/{userId}")
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
    @PostMapping("/login")
    ApiReponse<AuthenticationReponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {
        var result = authenticationService.authenticate(authenticationRequest);
        System.out.println(result.isAuthenticated());
        if (result.isAuthenticated() == false) {
            return ApiReponse.<AuthenticationReponse>builder()
                    .code(403)
                    .message("Sai pass hoac username ban oi")
                    .build();
        }

        return ApiReponse.<AuthenticationReponse>builder()
                //Cau truc tra ve json (mess, result(token, authen(Status auth))
                .message("Authentication Successful")
                .result(result)
                .build();

    }
}

