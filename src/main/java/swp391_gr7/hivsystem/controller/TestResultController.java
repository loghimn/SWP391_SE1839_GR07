package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;
import swp391_gr7.hivsystem.service.ReExaminationService;
import swp391_gr7.hivsystem.service.TestResultService;
import swp391_gr7.hivsystem.dto.response.TestResultResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/testresults")
public class TestResultController {

    @Autowired
    private TestResultService testResultService;
    @Autowired
    private ReExaminationService reExaminationService;

    @PostMapping("/{appointmentId}")
    public ApiResponse<TestResultResponse> addTestResult(
            @PathVariable Integer appointmentId,
            @PathVariable Integer trearmentplanId,
            @RequestBody TestResultCreateRequest request) {
        TestResults result = testResultService.addTestResult(appointmentId, trearmentplanId, request);
        TestResultResponse response = result != null ? convertToResponse(result) : null;
        if (response != null && result.isRe_examination()) {
            reExaminationService.handleReExamination(result);
        }

        return ApiResponse.<TestResultResponse>builder()
                .code(response != null ? 200 : 400)
                .result(response)
                .message(response != null ? "Success" : testResultService.getError())
                .build();
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<TestResultResponse>> getTestResultsByCustomer(
            @PathVariable int customerId) {
        List<TestResults> results = testResultService.getTestResultsByCustomer(customerId);
        List<TestResultResponse> responses = results.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ApiResponse.<List<TestResultResponse>>builder()
                .code(200)
                .result(responses)
                .message("Success")
                .build();
    }

    private TestResultResponse convertToResponse(TestResults result) {
        return TestResultResponse.builder()
                .id(result.getTestResultID())
                .testType(result.getTestType())
                .resultValue(result.isResultValue())
                .testDate(result.getTestDate())
                .notes(result.getNotes())
                .reExamination(result.isRe_examination())
                .appointmentId(result.getAppointments().getAppointmentId())
                .customerId(result.getCustomers().getCustomerId())
                .doctorId(result.getDoctors().getDoctorId())
                .build();
    }
}