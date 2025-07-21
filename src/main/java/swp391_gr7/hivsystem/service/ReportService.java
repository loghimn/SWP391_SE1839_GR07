package swp391_gr7.hivsystem.service;

import jakarta.servlet.http.HttpServletResponse;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.dto.response.AppointmentStatisticsResponse;
import swp391_gr7.hivsystem.dto.response.UserRoleStatisticsResponse;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    void exportCustomerToCSV(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException;

    void exportStaffToCSV(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException;

    void exportDoctorToCSV(HttpServletResponse response, ReportCreateRequest request, int id);

    void exportAppointmentToCSV(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException;

    List<UserRoleStatisticsResponse> getUserStatisticsByMonthAndRole();

    List<AppointmentStatisticsResponse> getAppointmentIsDoneStatisticsByMonth();
}
