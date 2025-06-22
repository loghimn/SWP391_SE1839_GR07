package swp391_gr7.hivsystem.controller;

import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.response.AuthenticationResponse;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.dto.response.IntrospectReponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.UserRepository;
import swp391_gr7.hivsystem.service.AuthenticationService;
import swp391_gr7.hivsystem.service.UserService;

import java.util.List;


@RestController
//CRUD
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    //Create user
    //http://localhost:8080/user/create
    @PostMapping("/customer/register")
    public ApiResponse<Boolean> registerUserAndCustomer(@RequestBody @Valid UserAndCustomerCreateRequest request) {
        boolean result = userService.registerUserAndCustomer(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }


    @PostMapping("/doctor/register")
    public ApiResponse<Boolean> registerUserAndDoctor(@RequestBody @Valid UserAndDoctorCreateRequest request) {
        var result = userService.registerUserAndDoctor(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/manager/register")
    public ApiResponse<Boolean> registerUserAndManager(@RequestBody @Valid UserAndManagerCreateRequest request) {
        var result = userService.registerUserAndManager(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/staff/register")
    public ApiResponse<Boolean> registerUserAndStaff(@RequestBody @Valid UserAndStaffCreateRequest request) {
        var result = userService.registerUserAndStaff(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PostMapping("/admin/register")
    public ApiResponse<Boolean> registerUserAndAdmin(@RequestBody @Valid UserAndAdminCreateRequest request) {
        var result = userService.registerUserAndAdmin(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    //Get user by id
    //http://localhost:8080/user/userId  id tu phat sinh
    @GetMapping("/{userId}")
    @PostAuthorize("returnObject.username == authentication.name") // Only the user can access their own information
    public Users getUser(@PathVariable int userId) {
        return userService.findUserByUserId(userId);
    }

    // Get my info
    @GetMapping("/myInfo")
    public ApiResponse<Users> getMyInfo(){
        Users user = userService.getMyInfo();
        return ApiResponse.<Users>builder()
                .result(user)
                .message("Success")
                .build();
    }

    // Get all users
    //http://localhost:8080/user/all
    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/all")
    public ApiResponse<List<Users>> getAllUsers() {
        List<Users> users = userService.findAllUsers();
        return ApiResponse.<List<Users>>builder()
                .result(users)
                .message("Success")
                .build();
    }


    //Delete user by id
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {
        var result = authenticationService.authenticate(authenticationRequest);
        System.out.println(result.isAuthenticated());
        if (!result.isAuthenticated()) {
            return ApiResponse.<AuthenticationResponse>builder()
                    .result(AuthenticationResponse.builder()
                            .authenticated(false)
                            .build())
                    .build();
        }

        return ApiResponse.<AuthenticationResponse>builder()
                //Cau truc tra ve json (mess, result(token, authen(Status auth))
                .message("Authentication Successful")
                .result(result)
                .build();
    }
}

