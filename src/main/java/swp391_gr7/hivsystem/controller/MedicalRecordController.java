package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.model.MedicalRecords;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.MedicalRecordService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicalrecords")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping
    public List<MedicalRecords> getAll() {
        return medicalRecordService.getAll();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/{id}")
    public Optional<MedicalRecords> getById(@PathVariable int id) {
        return medicalRecordService.getById(id);
    }

    @PreAuthorize("hasRole('Customer')")
    @PostMapping("/create")
    public ApiResponse<MedicalRecords> create(@RequestHeader("Authorization") String authorizationHeader,
                                              @RequestBody MedicalRecordCreateRequest request) {
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);
        MedicalRecords record = medicalRecordService.addMedicalRecord(customerId, request);
        boolean result = record != null;
        return ApiResponse.<MedicalRecords>builder()
                .result(record)
                .message(result ? "Success" : "Creation failed")
                .build();
    }

    @PreAuthorize("hasRole('Customer')")
    @PutMapping("/update")
    public MedicalRecords update(@RequestBody MedicalRecordCreateRequest request,
                                 @RequestHeader("Authorization") String authorizationHeader) {
        // Optionally set the ID in the request if needed
        // request.setId(id);

        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractDoctorId(token);

        return medicalRecordService.updateMedicalRecord(customerId, request);
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/myrecord")
    public MedicalRecords getMyMedicalRecord(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);
        return medicalRecordService.getMyMedicalRecord(customerId);
    }
}