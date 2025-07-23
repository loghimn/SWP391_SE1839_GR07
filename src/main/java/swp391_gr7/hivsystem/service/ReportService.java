package swp391_gr7.hivsystem.service;

import jakarta.servlet.http.HttpServletResponse;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.dto.response.AppointmentStatisticsResponse;
import swp391_gr7.hivsystem.dto.response.UserRoleStatisticsResponse;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    void exportCustomer(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException;

    void exportStaff(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException;

    void exportDoctor(HttpServletResponse response, ReportCreateRequest request, int id);

    void exportAppointment(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException;

    List<UserRoleStatisticsResponse> getUserStatisticsByMonthAndRole();

    List<AppointmentStatisticsResponse> getAppointmentIsDoneStatisticsByMonth();
}
