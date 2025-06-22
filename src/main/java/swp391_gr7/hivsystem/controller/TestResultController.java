package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;
import swp391_gr7.hivsystem.service.ReExaminationService;
import swp391_gr7.hivsystem.service.TestResultService;

import java.util.List;

@RestController
@RequestMapping("/api/testresults")
public class TestResultController {
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private ReExaminationService reExaminationService;

    @PostMapping("/{appointmentId}/{treatmentPlanId}")
    public ApiResponse<TestResults> addTestResult(
            @PathVariable int appointmentId,
            @PathVariable int treatmentPlanId,
            @RequestBody TestResultCreateRequest request) {
        TestResults result = testResultService.addTestResult(request);

        if (result != null && result.isRe_examination()) {
            reExaminationService.handleReExamination(result);
        }

        return ApiResponse.<TestResults>builder()
                .code(result != null ? 200 : 400)
                .result(result)
                .message(result != null ? "Success" : testResultService.getError())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TestResults> getTestResult(@PathVariable int id) {
        TestResults result = testResultService.getTestResultById(id);

        return ApiResponse.<TestResults>builder()
                .code(result != null ? 200 : 404)
                .result(result)
                .message(result != null ? "Success" : "Test result not found")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TestResults> updateTestResult(
            @PathVariable int id,
            @RequestBody TestResultCreateRequest request) {
        TestResults result = testResultService.updateTestResult(id, request);

        return ApiResponse.<TestResults>builder()
                .code(result != null ? 200 : 400)
                .result(result)
                .message(result != null ? "Success" : testResultService.getError())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteTestResult(@PathVariable int id) {
        boolean deleted = testResultService.deleteTestResult(id);

        return ApiResponse.<Boolean>builder()
                .code(deleted ? 200 : 404)
                .result(deleted)
                .message(deleted ? "Success" : "Test result not found")
                .build();
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<TestResults>> getTestResultsByCustomer(
            @PathVariable int customerId) {
        List<TestResults> results = testResultService.getTestResultsByCustomer(customerId);

        return ApiResponse.<List<TestResults>>builder()
                .code(200)
                .result(results)
                .message(results.isEmpty() ? "No results found" : "Success")
                .build();
    }
}