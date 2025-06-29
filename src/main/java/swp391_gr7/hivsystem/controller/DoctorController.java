package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.UserService;

@RestController
@RequestMapping("/api/doctor")
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {

    private final UserService userService;

    public DoctorController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/update")
    public ApiResponse<Boolean> updateCustomer(@RequestBody @Valid UserAndDoctorUpdateRequest request,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorID = new JWTUtils().extractDoctorId(token);

        boolean result = userService.updateUserAndDoctor(doctorID, request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Update successful" : "Update failed")
                .build();
    }
}
