package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.service.TreatmentPlanService;

@RestController
@RequestMapping("/api/treatmentplan")
public class TreatmentPlanController {
    @Autowired
    TreatmentPlanService treatmentPlanService;
    @PostMapping("/create")
    public ApiResponse<Boolean> createTreatmentPlan(@RequestBody TreatmentPlansCreateRequest request) {
        boolean result = treatmentPlanService.createTreatmentPlan(request);
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
