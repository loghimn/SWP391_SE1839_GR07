package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.TreatmentPlanService;

@RestController
@RequestMapping("/api/treatmentplan")
@SecurityRequirement(name = "bearerAuth")
public class TreatmentPlanController {
    @Autowired
    TreatmentPlanService treatmentPlanService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Boolean> createTreatmentPlan(@RequestBody TreatmentPlansCreateRequest request,
                                                    @RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        boolean result = treatmentPlanService.createTreatmentPlan(doctorId, request);
        if (result) {
            return ApiResponse.<Boolean>builder()
                    .message("Success")
                    .result(result)
                    .build();
        }
        return ApiResponse.<Boolean>builder()
                .message("Failed")
                .result(result)
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/get/{id}")
    public ApiResponse<?> getTreatmentPlanById(@PathVariable int id) {
        return ApiResponse.<Object>builder()
                .result(treatmentPlanService.getTreatmentPlanById(id))
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/getall")
    public ApiResponse<?> getAllTreatmentPlans() {
        return ApiResponse.<Object>builder()
                .result(treatmentPlanService.getAll())
                .message("Success")
                .build();
    }


    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/getmytreatmentplan_doctor")
    public ApiResponse<?> getMyTreatmentPlanDoctor(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        return ApiResponse.<Object>builder()
                .result(treatmentPlanService.getMyTreatmentPlantDoc(doctorId))
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/getmytreatmentplan_customer")
    public ApiResponse<?> getMyTreatmentPlanCustomer(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractDoctorId(token);

        return ApiResponse.<Object>builder()
                .result(treatmentPlanService.getMyTreatmentPlantCus(customerId))
                .message("Success")
                .build();
    }


    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{id}")
    public ApiResponse<?> updateTreatmentPlan(@PathVariable int id, @RequestBody TreatmentPlansCreateRequest request) {
        return ApiResponse.<Object>builder()
                .result(treatmentPlanService.updateTreatmentPlan(id, request))
                .message("Success")
                .build();
    }
}
