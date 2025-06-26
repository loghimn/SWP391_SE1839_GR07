package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/create")
    public ApiResponse<Boolean> createTreatmentPlan(@RequestBody TreatmentPlansCreateRequest request,
                                                    @RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        boolean result = treatmentPlanService.createTreatmentPlan(doctorId, request);
        if(result){
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
}
