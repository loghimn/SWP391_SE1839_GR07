package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.model.Consultations;
import swp391_gr7.hivsystem.service.ConsultationService;
import swp391_gr7.hivsystem.dto.request.ConsultationCreateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@RequestBody @Valid ConsultationCreateRequest request) {

        Consultations consultation = consultationService.createConsultation(request);
        boolean result = consultation != null;
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/{id}")
    public ApiResponse<Consultations> getById(@PathVariable int id) {
        try {
            Consultations consultation = consultationService.getConsultationById(id);
            if (consultation == null) {
                return ApiResponse.<Consultations>builder()
                        .code(404)
                        .result(null)
                        .message("Consultation not found")
                        .build();
            }

            return ApiResponse.<Consultations>builder()
                    .code(200)
                    .result(consultation)
                    .message("Success")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Consultations>builder()
                    .code(400)
                    .result(null)
                    .message("Get failed: " + e.getMessage())
                    .build();
        }
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<Consultations>> getByCustomer(@PathVariable int customerId) {
        try {
            List<Consultations> consultations = consultationService.getConsultationsByCustomer(customerId);
            return ApiResponse.<List<Consultations>>builder()
                    .code(200)
                    .result(consultations)
                    .message(consultations.isEmpty() ? "No consultations found" : "Success")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<Consultations>>builder()
                    .code(400)
                    .result(null)
                    .message("Get failed: " + e.getMessage())
                    .build();
        }
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/{doctorId}")
    public ApiResponse<List<Consultations>> getByDoctor(@PathVariable int doctorId) {
        try {
            List<Consultations> consultations = consultationService.getConsultationsByDoctor(doctorId);
            return ApiResponse.<List<Consultations>>builder()
                    .code(200)
                    .result(consultations)
                    .message(consultations.isEmpty() ? "No consultations found" : "Success")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<Consultations>>builder()
                    .code(400)
                    .result(null)
                    .message("Get failed: " + e.getMessage())
                    .build();
        }
    }
}
