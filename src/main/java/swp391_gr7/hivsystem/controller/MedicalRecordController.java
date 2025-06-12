package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiResponse;
import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.model.MedicalRecords;
import swp391_gr7.hivsystem.service.MedicalRecordService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicalrecords")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecords> getAll() {
        return medicalRecordService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MedicalRecords> getById(@PathVariable int id) {
        return medicalRecordService.getById(id);
    }

    @PostMapping("/create")
    public ApiResponse<MedicalRecords> create(@RequestBody MedicalRecordCreateRequest request) {
        MedicalRecords record = medicalRecordService.addMedicalRecord(request);
        boolean result = record != null;
        return ApiResponse.<MedicalRecords>builder()
                .result(record)
                .message(result ? "Success" : "Creation failed")
                .build();
    }

    @PutMapping("/update/{id}")
    public MedicalRecords update(@PathVariable int id, @RequestBody MedicalRecordCreateRequest request) {
        // Optionally set the ID in the request if needed
        // request.setId(id);
        return medicalRecordService.addMedicalRecord(request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        medicalRecordService.deleteById(id);
    }
}