package swp391_gr7.hivsystem.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndStaffUpdateRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.UserService;

@Controller
@RequestMapping("/api/staff")
public class StaffController {

    private final UserService userService;

    public StaffController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('Staff')")
    @RequestMapping("/update")
    public ApiResponse<Boolean> updateCustomer(@RequestBody @Valid UserAndStaffUpdateRequest request,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int staffId = new JWTUtils().extractStaffId(token);

        boolean result = userService.updateUserAndStaff(staffId, request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Update successful" : "Update failed")
                .build();
    }
}
