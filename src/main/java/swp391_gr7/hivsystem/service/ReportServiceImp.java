package swp391_gr7.hivsystem.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Reports;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.ReportRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void exportUserToCSV(HttpServletResponse response,
                                ReportCreateRequest request, int id) throws IOException {
        // Lấy danh sách User
        List<Users> users = userRepository.findAll();
        if(users.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        Managers manager = managerRepository.findManagerById(1);
        if(manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        // Xử lý Manager
//        Optional<Managers> managerOpt = managerRepository.findManagerByMail(request.getManagerMail());
//        Managers manager = null;
//        if(managerOpt.isEmpty()) {
//            error += "Manager not found with mail";
//        } else {
//            manager = managerOpt.get();
//        }

        // Ghi ra file CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=report.csv");

        PrintWriter printWriter = getPrintWriterUser(response, users);

        printWriter.flush();

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());

        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    @Override
    public void exportAppointmentToCSV(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException {
        // Lấy danh sách Appointment
        List<Appointments> appointments = appointmentRepository.findAll();
        if(appointments.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }

        // Xử lý Manager
//        Optional<Managers> managerOpt = managerRepository.findManagerByMail(request.getManagerMail());
//        Managers manager = null;
//        if(managerOpt.isEmpty()) {
//            error += "Manager not found with mail";
//        } else {
//            manager = managerOpt.get();
//        }
        Managers manager = managerRepository.findManagerById(1);
        if(manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        // Ghi ra file CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=appointment_report.csv");

        PrintWriter printWriter = getPrintWriterAppointment(response, appointments);
        printWriter.flush();
        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());
        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static PrintWriter getPrintWriterUser(HttpServletResponse response, List<Users> users) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("username, email, phone, fullName, role");

        // Chạy vòng lặp tìm user
        for (Users user : users) {
            if (user.getCreatedAt().isAfter(LocalDateTime.now().minusDays(2))) { // Chỉ lấy user được tạo trong 2 ngày qua
                printWriter.printf("%s,%s,%s,%s,%s%n",
                        escape(user.getUsername()),
                        escape(user.getEmail()),
                        escape(user.getPhone()),
                        escape(user.getFullName()),
                        escape(user.getRole())
                );
            }
        }
        return printWriter;
    }

    private static PrintWriter getPrintWriterAppointment(HttpServletResponse response, List<Appointments> appointments) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("appointmentID, customerName, doctorName, appointmentDate, status");

        // Chạy vòng lặp tìm appointment
        for (Appointments appointment : appointments) {
            printWriter.printf("%s,%s,%s,%s,%s%n",
                    escape(String.valueOf(appointment.getAppointmentId())),
                    escape(appointment.getCustomers().getUsers().getFullName()),
                    escape(appointment.getDoctors().getUsers().getFullName()),
                    escape(String.valueOf(appointment.getAppointmentTime())),
                    escape(appointment.isStatus() ? "true" : "false")
            );
        }
        return printWriter;
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.contains(",") ? "\"" + value.replace("\"", "\"\"") + "\"" : value;
    }
}

