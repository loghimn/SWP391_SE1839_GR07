package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.ReExaminationService;
import swp391_gr7.hivsystem.service.TestResultService;

import java.util.List;

@RestController
@RequestMapping("/api/testresults")
@SecurityRequirement(name = "bearerAuth")
public class TestResultController {
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private ReExaminationService reExaminationService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<TestResults> addTestResult(@RequestBody TestResultCreateRequest request) {
        TestResults result = testResultService.addTestResult(request);

        if (result != null && result.isRe_examination()) {
            reExaminationService.handleReExamination(result);
        }

        return ApiResponse.<TestResults>builder()
                .code(result != null ? 200 : 400)
                .result(result)
                .message(result != null ? "Success" : "Failed to create test result")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/{id}")
    public ApiResponse<TestResults> getTestResult(@PathVariable int id) {
        TestResults result = testResultService.getTestResultById(id);

        return ApiResponse.<TestResults>builder()
                .code(result != null ? 200 : 404)
                .result(result)
                .message(result != null ? "Success" : "Test result not found")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/{id}")
    public ApiResponse<TestResults> updateTestResult(
            @PathVariable int id,
            @RequestBody TestResultCreateRequest request) {
        TestResults result = testResultService.updateTestResult(id, request);

        return ApiResponse.<TestResults>builder()
                .code(result != null ? 200 : 400)
                .result(result)
                .message(result != null ? "Success" : "Failed to update test result")
                .build();
    }


    @PreAuthorize("hasRole('Doctor')")
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

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/myresults-customer")
    public ApiResponse<List<TestResults>> getMyTestResultsCustomer(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);

        List<TestResults> results = testResultService.getMyTestResultsCus(customerId);

        return ApiResponse.<List<TestResults>>builder()
                .code(200)
                .result(results)
                .message(results.isEmpty() ? "No results found" : "Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/myresults-doctor")
    public ApiResponse<List<TestResults>> getMyTestResultsDoctor(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        List<TestResults> results = testResultService.getMyTestResultsDoc(doctorId);

        return ApiResponse.<List<TestResults>>builder()
                .code(200)
                .result(results)
                .message(results.isEmpty() ? "No results found" : "Success")
                .build();
    }

}