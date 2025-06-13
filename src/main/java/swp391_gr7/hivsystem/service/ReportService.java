package swp391_gr7.hivsystem.service;

import jakarta.servlet.http.HttpServletResponse;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;

import java.io.IOException;

public interface ReportService {
    void exportUserToCSV(HttpServletResponse response, ReportCreateRequest request) throws IOException;
    void exportAppointmentToCSV(HttpServletResponse response, ReportCreateRequest request) throws IOException;
}
