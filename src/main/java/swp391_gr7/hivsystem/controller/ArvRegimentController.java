package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.SuggestMedicationRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.service.ArvRegimentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/arvregiment")
public class ArvRegimentController {
    @Autowired
    private ArvRegimentService arvRegimentService;
    @PostMapping("/create")
    public ApiResponse<Boolean> createArvRegiment(@RequestBody ArvRegimentCreateRequest request) {
        boolean result = arvRegimentService.createArvRegiment(request);
        if (result) {
            return ApiResponse.<Boolean>builder()
                    .result(result)
                    .message("Successfully created arvregiment")
                    .build();
        }
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Fail created arvregiment")
                .build();
    }

    @GetMapping("/suggest/medications")
    public ApiResponse<List> suggestMedications(SuggestMedicationRequest requset) {
        List list = new ArrayList();
        list =  (arvRegimentService.suggestArvMedication(requset.getTreatmentPlansId()));
        if (list == null) {
            return ApiResponse.<List>builder()
                    .message("Fail")
                    .build();
        }
        return ApiResponse.<List>builder()
                .message("Suggested Medications")
                .result(list)
                .build();

    }

}
