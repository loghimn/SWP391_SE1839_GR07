package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.ArvMedicationCreateRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.service.ArvMedicationService;
import swp391_gr7.hivsystem.service.JWTUtils;

import java.util.List;

@RestController
@RequestMapping("/api/medication")
@SecurityRequirement(name = "bearerAuth")
public class ArvMedicationController {
    @Autowired
    ArvMedicationService arvMedicationService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Boolean> createMedication(@RequestBody ArvMedicationCreateRequest request,
                                                 @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        boolean result = arvMedicationService.addArvMedication(request, doctorId);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Successfully created arvmedication" : "Fail created arvmedication")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{code}")
    public ApiResponse<Boolean> updateMedication(@RequestBody ArvMedicationCreateRequest request,
                                                 @PathVariable String code,
                                                 @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        boolean result = arvMedicationService.updateArvMedication(request, code);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Successfully updated arvmedication" : "Fail updated arvmedication")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/delete/{code}")
    public ApiResponse<Boolean> deleteMedication(@PathVariable String code) {
        boolean result = arvMedicationService.deleteArvMedication(code);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Successfully delete arvmedication" : "Fail delete arvmedication")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping
    public ApiResponse<List> listMedication() {
        List result = arvMedicationService.showAllListMedication();
        return ApiResponse.<List>builder()
                .result(result)
                .message(result != null ? "Successfully fetched arvmedication" : "Fail fetched arvmedication")
                .build();
    }
}