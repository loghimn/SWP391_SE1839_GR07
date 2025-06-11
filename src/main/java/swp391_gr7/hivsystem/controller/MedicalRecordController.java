package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.model.MedicalRecord;
import swp391_gr7.hivsystem.service.MedicalRecordService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicalrecords")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecord> getAll() {
        return medicalRecordService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MedicalRecord> getById(@PathVariable int id) {
        return medicalRecordService.getById(id);
    }

    @PostMapping("/create")
    public ApiReponse<MedicalRecord> create(@RequestBody MedicalRecordCreateRequest request) {
        MedicalRecord record = medicalRecordService.addMedicalRecord(request);
        boolean result = record != null;
        return ApiReponse.<MedicalRecord>builder()
                .result(record)
                .message(result ? "Success" : "Creation failed")
                .build();
    }

    @PutMapping("/update/{id}")
    public MedicalRecord update(@PathVariable int id, @RequestBody MedicalRecordCreateRequest request) {
        // Optionally set the ID in the request if needed
        // request.setId(id);
        return medicalRecordService.addMedicalRecord(request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        medicalRecordService.deleteById(id);
    }
}