package swp391_gr7.hivsystem.controller;

import com.nimbusds.jose.JOSEException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Staffs;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.UserRepository;
import swp391_gr7.hivsystem.service.*;

import java.util.List;
import java.util.Map;


@RestController
//CRUD
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private StaffService staffService;

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

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/doctor/register")
    public ApiResponse<Boolean> registerUserAndDoctor(@RequestBody @Valid UserAndDoctorCreateRequest request) {
        var result = userService.registerUserAndDoctor(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/manager/register")
    public ApiResponse<Boolean> registerUserAndManager(@RequestBody @Valid UserAndManagerCreateRequest request) {
        var result = userService.registerUserAndManager(request);
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Success" : "Registration failed")
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
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
        if (userService.findUserByUserId(userId) == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return userService.findUserByUserId(userId);
    }

    // Get my info
    @GetMapping("/myInfo")
    @PostAuthorize("returnObject.result.username == authentication.name")
    // Only the user can access their own information
    public Users getMyInfo() {
        Users user = userService.getMyInfo();
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }

    // Get all users
    //http://localhost:8080/user/all
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/all")
    public ApiResponse<List<Users>> getAllUsers() {
        List<Users> users = userService.findAllUsers();
        return ApiResponse.<List<Users>>builder()
                .result(users)
                .message("Success")
                .build();
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ApiResponse<Users> getUserById(@PathVariable int id) {
        Users user = userService.getUserById(id);
        return ApiResponse.<Users>builder()
                .result(user)
                .message("Success")
                .build();
    }

    @GetMapping("getCustomerById/{id}")
    @PreAuthorize("hasRole('Manager')")
    public ApiResponse<Customers> getCustomerById(@PathVariable int id) {
        Customers customers = customerService.getCustomerById(id);
        if (customers == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return ApiResponse.<Customers>builder()
                .result(customers)
                .message("Success")
                .build();
    }

    @GetMapping("/getDoctorById/{id}")
    @PreAuthorize("hasRole('Manager')")
    public ApiResponse<Doctors> getDoctorById(@PathVariable int id) {
        Doctors doctors = doctorService.findDoctorById(id);
        if (doctors == null) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        return ApiResponse.<Doctors>builder()
                .result(doctors)
                .message("Success")
                .build();
    }

    @GetMapping("/getStaffById/{id}")
    @PreAuthorize("hasRole('Manager')")
    public ApiResponse<Staffs> getStaffById(@PathVariable int id) {
        Staffs staffs = staffService.getStaffById(id);
        if (staffs == null) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }
        return ApiResponse.<Staffs>builder()
                .result(staffs)
                .message("Success")
                .build();
    }

    @GetMapping("/getAllCustomers")
    @PreAuthorize("hasRole('Manager')")
    public ApiResponse<List<Customers>> getAllCustomers() {
        List<Customers> customers = customerService.getAllCustomers();
        return ApiResponse.<List<Customers>>builder()
                .result(customers)
                .message("Success")
                .build();
    }

    @GetMapping("/getAllDoctors")
    @PreAuthorize("hasRole('Manager')")
    public ApiResponse<List<Doctors>> getAllDoctors() {
        List<Doctors> doctors = doctorService.showAllDoctors();
        return ApiResponse.<List<Doctors>>builder()
                .result(doctors)
                .message("Success")
                .build();
    }

    @GetMapping("/getAllStaffs")
    @PreAuthorize("hasRole('Manager')")
    public ApiResponse<List<Staffs>> getAllStaffs() {
        List<Staffs> staffs = staffService.getAllStaffs();
        return ApiResponse.<List<Staffs>>builder()
                .result(staffs)
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

    @PostMapping("/google/register")
    public ApiResponse<AuthenticationResponse> googleLoginOrRegister(@RequestBody Map<String, String> payload) {
        String googleToken = payload.get("credential");
        AuthenticationResponse response = authenticationService.authenticateWithGoogle(googleToken);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .message(response.isAuthenticated() ? "Đăng nhập Google thành công" : "Thất bại")
                .build();
    }

}

