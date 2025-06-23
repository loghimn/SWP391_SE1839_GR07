package swp391_gr7.hivsystem.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.ReportService;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint to export user report
    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/users/export")
    public ApiResponse<Boolean> exportReport(HttpServletResponse response,
                                             @RequestBody ReportCreateRequest request,
                                             @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        // Extract userId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int id = new JWTUtils().extractManagerId(token);

        reportService.exportUserToCSV(response, request, id);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    // Endpoint to export appointment report
    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/appointments/export")
    public ApiResponse<Boolean> exportAppointmentReport(HttpServletResponse response,
                                                        @RequestBody ReportCreateRequest request,
                                                        @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        // Extract userId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int id = new JWTUtils().extractManagerId(token);

        reportService.exportUserToCSV(response, request, id);
        reportService.exportAppointmentToCSV(response, request, id);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }


}
