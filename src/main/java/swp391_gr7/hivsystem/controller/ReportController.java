package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.dto.response.AppointmentStatisticsResponse;
import swp391_gr7.hivsystem.dto.response.UserRoleStatisticsResponse;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.ReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@SecurityRequirement(name = "bearerAuth")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/customers/export")
    public ApiResponse<Boolean> exportCustomerReport(HttpServletResponse response,
                                             @Valid @RequestBody ReportCreateRequest request,
                                             @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        String token = authorizationHeader.replace("Bearer ", "");
        int managerId = new JWTUtils().extractManagerId(token);

        reportService.exportCustomer(response, request, managerId);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/staffs/export")
    public ApiResponse<Boolean> exportStaffReport(HttpServletResponse response,
                                                  @Valid @RequestBody ReportCreateRequest request,
                                                  @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        String token = authorizationHeader.replace("Bearer ", "");
        int managerId = new JWTUtils().extractManagerId(token);

        reportService.exportStaff(response, request, managerId);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/doctors/export")
    public ApiResponse<Boolean> exportDoctorReport(HttpServletResponse response,
                                                  @Valid @RequestBody ReportCreateRequest request,
                                                  @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        String token = authorizationHeader.replace("Bearer ", "");
        int managerId = new JWTUtils().extractManagerId(token);

        reportService.exportDoctor(response, request, managerId);
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
        int managerId = new JWTUtils().extractManagerId(token);

        reportService.exportAppointment(response, request, managerId);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/users/statistics")
    public ApiResponse<List<UserRoleStatisticsResponse>> getUserStatisticsByMonth(){
        return ApiResponse.<List<UserRoleStatisticsResponse>>builder()
                .result(reportService.getUserStatisticsByMonthAndRole())
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/appointment/statistics/is-done")
    public ApiResponse<List<AppointmentStatisticsResponse>> getAppointmentStatisticsIsDoneByMonth(){
        return ApiResponse.<List<AppointmentStatisticsResponse>>builder()
                .result(reportService.getAppointmentIsDoneStatisticsByMonth())
                .message("Success")
                .build();
    }

}
