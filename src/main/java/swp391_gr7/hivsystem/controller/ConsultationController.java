package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.model.Consultations;
import swp391_gr7.hivsystem.service.ConsultationService;
import swp391_gr7.hivsystem.dto.request.ConsultationCreateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @PostMapping("/create")
    public ApiResponse<Boolean> create(@RequestBody ConsultationCreateRequest request) {
        try {
            Consultations consultation = consultationService.createConsultation(request);
            boolean success = consultation != null;

            return ApiResponse.<Boolean>builder()
                    .code(success ? 200 : 400)
                    .result(success)
                    .message(success ? "Success" : consultationService.getError())
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Boolean>builder()
                    .code(400)
                    .result(false)
                    .message("Create failed: " + e.getMessage())
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<Consultations> getById(@PathVariable Long id) {
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

    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<Consultations>> getByCustomer(@PathVariable Long customerId) {
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

    @GetMapping("/doctor/{doctorId}")
    public ApiResponse<List<Consultations>> getByDoctor(@PathVariable Long doctorId) {
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

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        try {
            boolean success = consultationService.deleteConsultation(id);
            return ApiResponse.<Boolean>builder()
                    .code(success ? 200 : 404)
                    .result(success)
                    .message(success ? "Success" : "Consultation not found")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Boolean>builder()
                    .code(400)
                    .result(false)
                    .message("Delete failed: " + e.getMessage())
                    .build();
        }
    }
}