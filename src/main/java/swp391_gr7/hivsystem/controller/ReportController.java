package swp391_gr7.hivsystem.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.service.ReportService;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint to export user report
    @PostMapping("/users/export")
    public ApiResponse<Boolean> exportReport(HttpServletResponse response,
                                             @RequestBody ReportCreateRequest request) throws IOException {
        reportService.exportUserToCSV(response, request);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    // Endpoint to export appointment report
    @PostMapping("/appointments/export")
    public ApiResponse<Boolean> exportAppointmentReport(HttpServletResponse response,
                                                        @RequestBody ReportCreateRequest request) throws IOException {
        reportService.exportAppointmentToCSV(response, request);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }



}
