package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.SuggestMedicationRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.service.ArvRegimentService;
import swp391_gr7.hivsystem.service.JWTUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/arvregiment")
@SecurityRequirement(name = "bearerAuth")
public class ArvRegimentController {
    @Autowired
    private ArvRegimentService arvRegimentService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Boolean> createArvRegiment(@RequestBody ArvRegimentCreateRequest request,
                                                  @RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        boolean result = arvRegimentService.createArvRegiment(request, doctorId);
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

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/all")
    public ApiResponse<List<ArvRegiments>> getAllArvRegiments() {
        List<ArvRegiments> list = arvRegimentService.getAllArvRegiments();
        return ApiResponse.<List<ArvRegiments>>builder()
                .result(list)
                .message("Fetched all ARV regiments")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/get/{arvregimentid}")
    public ApiResponse<ArvRegiments> getArvRegimentById(@PathVariable int arvregimentid) {
        Optional<ArvRegiments> optional = arvRegimentService.getArvRegimentById(arvregimentid);
        return optional.map(arvRegiments -> ApiResponse.<ArvRegiments>builder()
                        .result(arvRegiments)
                        .message("Fetched ARV regiment")
                        .build())
                .orElseGet(() -> ApiResponse.<ArvRegiments>builder()
                        .message("ARV regiment not found")
                        .build());
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> updateArvRegiment(@PathVariable int id,
                                                  @RequestBody ArvRegimentCreateRequest request) {
        boolean result = arvRegimentService.updateArvRegiment(id, request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Successfully updated arvregiment" : "Fail updated arvregiment")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/my-arvregiments")
    public ApiResponse<List<ArvRegiments>> getMyArvRegiments(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        List<ArvRegiments> list = arvRegimentService.getMyArvRegiments(doctorId);
        return ApiResponse.<List<ArvRegiments>>builder()
                .result(list)
                .message("Fetched my ARV regiments")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/get/{doctorId}")
    public ApiResponse<List<ArvRegiments>> getArvRegimentsByDoctorId(@PathVariable int doctorId) {
        List<ArvRegiments> list = arvRegimentService.getArvRegimentsByDoctorId(doctorId);
        return ApiResponse.<List<ArvRegiments>>builder()
                .result(list)
                .message("Fetched ARV regiments by doctor ID")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/suggest/medications")
    public ApiResponse<List<ArvMedications>> suggestMedications(@RequestBody SuggestMedicationRequest request) {
        List<ArvMedications> list = arvRegimentService.suggestArvMedication(request.getTreatmentPlansId());
        if (list == null || list.isEmpty()) {
            return ApiResponse.<List<ArvMedications>>builder()
                    .message("Fail")
                    .build();
        }
        return ApiResponse.<List<ArvMedications>>builder()
                .message("Suggested Medications")
                .result(list)
                .build();
    }
}