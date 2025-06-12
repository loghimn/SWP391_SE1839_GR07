package swp391_gr7.hivsystem.controller;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiResponse;
import swp391_gr7.hivsystem.dto.reponse.AuthenticationResponse;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.UserRepository;
import swp391_gr7.hivsystem.service.AuthenticationService;
import swp391_gr7.hivsystem.service.UserService;


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
    public ApiResponse<Boolean> registerUserAndCustomer(@RequestBody UserAndCustomerCreateRequest request) {
        boolean result = userService.registerUserAndCustomer(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }


    @PostMapping("/doctor/register")
    public ApiResponse<Boolean> registerUserAndDoctor(@RequestBody UserAndDoctorCreateRequest request) {
        var result = userService.registerUserAndDoctor(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/manager/register")
    public ApiResponse<Boolean> registerUserAndManager(@RequestBody UserAndManagerCreateRequest request) {
        var result = userService.registerUserAndManager(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/staff/register")
    public ApiResponse<Boolean> registerUserAndStaff(@RequestBody UserAndStaffCreateRequest request) {
        var result = userService.registerUserAndStaff(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/admin/register")
    public ApiResponse<Boolean> registerUserAndAdmin(@RequestBody UserAndAdminCreateRequest request) {
        boolean result = userService.registerUserAndAdmin(request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    //Get user by id
    //http://localhost:8080/user/userId  id tu phat sinh
    @GetMapping("/{userId}")
    public Users getUser(@PathVariable int userId) {
        return userService.findUserByUserId(userId);
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
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {
        var result = authenticationService.authenticate(authenticationRequest);
        System.out.println(result.isAuthenticated());
        if (result.isAuthenticated() == false) {
            return ApiResponse.<AuthenticationResponse>builder()
                    .code(403)
                    .message("Sai pass hoac username ban oi")
                    .build();
        }

        return ApiResponse.<AuthenticationResponse>builder()
                //Cau truc tra ve json (mess, result(token, authen(Status auth))
                .message("Authentication Successful")
                .result(result)
                .build();

    }
}

