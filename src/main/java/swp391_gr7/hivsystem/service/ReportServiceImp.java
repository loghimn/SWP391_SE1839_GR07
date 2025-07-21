package swp391_gr7.hivsystem.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ReportCreateRequest;
import swp391_gr7.hivsystem.dto.response.AppointmentStatisticsResponse;
import swp391_gr7.hivsystem.dto.response.UserRoleStatisticsResponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void exportCustomerToCSV(HttpServletResponse response,
                                    ReportCreateRequest request, int id) throws IOException {
        // Lấy danh sách Customer
        List<Customers> allCustomers = customerRepository.findAll();
        if (allCustomers.isEmpty()) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        // Lọc theo tháng và năm
        List<Customers> filterCustomers = new ArrayList<>();
        for (Customers customer : allCustomers) {
            if (customer.getUsers().getCreatedAt().getMonthValue() == request.getMonth()
                    && customer.getUsers().getCreatedAt().getYear() == request.getYear()) {
                filterCustomers.add(customer);
            }
        }

        getPrintWriterCustomer(response, filterCustomers, request.getMonth(), request.getYear());

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());

        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static void getPrintWriterCustomer(HttpServletResponse response, List<Customers> customers, int month, int year) throws IOException {
        String fileName = String.format("customer_report_%02d_%d.csv", month, year);

        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Ghi BOM - UTF-8
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(0xEF);
        outputStream.write(0xBB);
        outputStream.write(0xBF);

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {
            writer.println("Tên đăng nhập,email,Số điện thoại,Họ và tên,Ngày sinh,Địa chỉ,Giới tính,Vai trò,Ngày tạo");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Customers customer : customers) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                        escape(customer.getUsers().getUsername()),
                        escape(customer.getUsers().getEmail()),
                        "\"\t" + customer.getUsers().getPhone() + "\"",
                        escape(customer.getUsers().getFullName()),
                        customer.getUsers().getDateOfBirth() != null
                                ? customer.getUsers().getDateOfBirth().format(formatter)
                                : "",
                        escape(customer.getAddress()),
                        customer.getUsers().getGender() != null ? customer.getUsers().getGender() : "",
                        escape(customer.getUsers().getRole()),
                        customer.getUsers().getCreatedAt() != null
                                ? customer.getUsers().getCreatedAt().toLocalDate().format(formatter)
                                : ""
                );
            }
            writer.flush();
        }
    }


    @Override
    public void exportStaffToCSV(HttpServletResponse response, ReportCreateRequest request, int id) {
        // Lấy danh sách Staff
        List<Staffs> allStaffs = staffRepository.findAllStaff();
        if (allStaffs.isEmpty()) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }

        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        // Lọc theo tháng và năm
        List<Staffs> filterStaffs = new ArrayList<>();
        for (Staffs staff : allStaffs) {
            if (staff.getUsers().getCreatedAt().getMonthValue() == request.getMonth()
                    && staff.getUsers().getCreatedAt().getYear() == request.getYear()) {
                filterStaffs.add(staff);
            }
        }

        try {
            getPrintWriterStaff(response, filterStaffs, request.getMonth(), request.getYear());
        } catch (IOException e) {
            throw new AppException(ErrorCode.FILE_ERROR);
        }

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());

        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static void getPrintWriterStaff(HttpServletResponse response, List<Staffs> staffs, int month, int year) throws IOException {
        String fileName = String.format("staff_report_%02d_%d.csv", month, year);

        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Ghi BOM - UTF-8
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(0xEF);
        outputStream.write(0xBB);
        outputStream.write(0xBF);

        try (
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {
            writer.println("Tên đăng nhập,email,Số điện thoại,Họ và tên,Ngày sinh,Ca làm việc,Giới tính,Vai trò,Ngày tạo");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Staffs staff : staffs) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                        escape(staff.getUsers().getUsername()),
                        escape(staff.getUsers().getEmail()),
                        "\"\t" + staff.getUsers().getPhone() + "\"",
                        escape(staff.getUsers().getFullName()),
                        staff.getUsers().getDateOfBirth() != null
                                ? staff.getUsers().getDateOfBirth().format(formatter)
                                : "",
                        staff.getWorkShift(),
                        staff.getUsers().getGender() != null ? staff.getUsers().getGender() : "",
                        escape(staff.getUsers().getRole()),
                        staff.getUsers().getCreatedAt() != null
                                ? staff.getUsers().getCreatedAt().toLocalDate().format(formatter)
                                : ""
                );
            }
            writer.flush();
        }
    }

    @Override
    public void exportDoctorToCSV(HttpServletResponse response, ReportCreateRequest request, int id) {
        // Lấy danh sách doctor
        List<Doctors> allDoctors = doctorRepository.findAllDoctors();
        if (allDoctors.isEmpty()) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }

        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        // Lọc theo tháng và năm
        List<Doctors> filterDoctors = new ArrayList<>();
        for (Doctors doctor : allDoctors) {
            if (doctor.getUsers().getCreatedAt().getMonthValue() == request.getMonth()
                    && doctor.getUsers().getCreatedAt().getYear() == request.getYear()) {
                filterDoctors.add(doctor);
            }
        }

        try {
            getPrintWriterDoctor(response, filterDoctors, request.getMonth(), request.getYear());
        } catch (IOException e) {
            throw new AppException(ErrorCode.FILE_ERROR);
        }

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());

        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static void getPrintWriterDoctor(HttpServletResponse response, List<Doctors> doctors, int month, int year) throws IOException {
        String fileName = String.format("doctor_report_%02d_%d.csv", month, year);

        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Ghi BOM - UTF-8
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(0xEF);
        outputStream.write(0xBB);
        outputStream.write(0xBF);

        try (
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {
            writer.println("Tên đăng nhập,email,Số điện thoại,Họ và tên,Ngày sinh,Giấy phép hành nghề,Năm kinh nghiệm,Giới tính,Vai trò,Ngày tạo");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Doctors doctor : doctors) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                        escape(doctor.getUsers().getUsername()),
                        escape(doctor.getUsers().getEmail()),
                        "\"\t" + doctor.getUsers().getPhone() + "\"",
                        escape(doctor.getUsers().getFullName()),
                        doctor.getUsers().getDateOfBirth() != null
                                ? doctor.getUsers().getDateOfBirth().format(formatter)
                                : "",
                        escape(doctor.getLicenseNumber()),
                        doctor.getYearExperience(),
                        doctor.getUsers().getGender() != null ? doctor.getUsers().getGender() : "",
                        escape(doctor.getUsers().getRole()),
                        doctor.getUsers().getCreatedAt() != null
                                ? doctor.getUsers().getCreatedAt().toLocalDate().format(formatter)
                                : ""
                );
            }
            writer.flush();
        }
    }


    @Override
    public void exportAppointmentToCSV(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException {
        // Lấy danh sách Appointment
        List<Appointments> allAppointments = appointmentRepository.findAll();
        if (allAppointments.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }

        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        // lọc theo tháng năm
        List<Appointments> filterAppointments = new ArrayList<>();
        for (Appointments appointment : allAppointments) {
            if (appointment.getSchedules().getWorkDate().getMonthValue() == request.getMonth()
                    && appointment.getSchedules().getWorkDate().getYear() == request.getYear()) {
                filterAppointments.add(appointment);
            }
        }

        // Ghi ra file CSV
        getPrintWriterAppointment(response, filterAppointments, request.getMonth(), request.getYear());

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());
        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static void getPrintWriterAppointment(HttpServletResponse response, List<Appointments> appointments, int month, int year) throws IOException {
        String fileName = String.format("appointment_report_%02d_%d.csv", month, year);

        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Ghi BOM - UTF-8
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(0xEF);
        outputStream.write(0xBB);
        outputStream.write(0xBF);

        try (
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {
            writer.println("Khách hàng,Bác sĩ,Nhân viên,Hồ sơ y tế,Ngày hẹn,Giờ bắt đầu,Giờ kết thúc,Ẩn danh,Loại lịch hẹn");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            for (Appointments appointment : appointments) {
                String customerName = appointment.isAnonymous()
                        ? "Ẩn danh"
                        : escape(appointment.getCustomers().getUsers().getFullName());
                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                        customerName,
                        escape(appointment.getDoctors().getUsers().getFullName()),
                        escape(appointment.getStaffs().getUsers().getFullName()),
                        escape(appointment.getMedicalRecords().getDiagnosis()),
                        appointment.getSchedules().getWorkDate() != null
                                ? appointment.getSchedules().getWorkDate().format(formatter)
                                : "",
                        appointment.getStartTime() != null
                                ? appointment.getStartTime().format(timeFormatter)
                                : "",
                        appointment.getEndTime() != null
                                ? appointment.getEndTime().format(timeFormatter)
                                : "",
                        appointment.isAnonymous()
                                ? "Ẩn danh" : "Không ẩn danh",
                        appointment.getAppointmentType().equals("Test HIV")
                                ? "Xét nghiệm HIV" : "Tư vấn"
                );
            }
            writer.flush();
        }
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.contains(",") ? "\"" + value.replace("\"", "\"\"") + "\"" : value;
    }

    @Override
    public List<UserRoleStatisticsResponse> getUserStatisticsByMonthAndRole() {
        List<Object[]> results = userRepository.countUsersByMonth();
        List<UserRoleStatisticsResponse> responses = new ArrayList<>();

        for (Object[] row : results) {
            int month = (Integer) row[0];
            int year = (Integer) row[1];
            String role = (String) row[2];
            long total = (Long) row[3];

            responses.add(new UserRoleStatisticsResponse(month, year, role, total));
        }
        return responses;
    }

    @Override
    public List<AppointmentStatisticsResponse> getAppointmentIsDoneStatisticsByMonth() {
        List<Object[]> results = appointmentRepository.countAppointmentIsDoneByMonth();
        List<AppointmentStatisticsResponse> responses = new ArrayList<>();

        for(Object[] row : results) {
            int month = (Integer) row[0];
            int year = (Integer) row[1];
            long totalAppointmentIsDone = (Long) row[2];

            responses.add(new AppointmentStatisticsResponse(month, year, totalAppointmentIsDone));
        }
        return responses;
    }
}

