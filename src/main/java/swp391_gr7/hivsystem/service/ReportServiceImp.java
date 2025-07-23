package swp391_gr7.hivsystem.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    public void exportCustomer(HttpServletResponse response,
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

        if("xlsx".equalsIgnoreCase(request.getFileType())){
            exportCustomerToExcel(response, filterCustomers, request.getMonth(), request.getYear());
        } else {
            exportCustomerToCSV(response, filterCustomers, request.getMonth(), request.getYear());
        }

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());

        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static void exportCustomerToCSV(HttpServletResponse response, List<Customers> customers, int month, int year) throws IOException {
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
                        customer.getUsers().getGender() != null
                                ? customer.getUsers().getGender().equals("male") ? "Nam" : "Nữ"
                                : "",
                        escape(customer.getUsers().getRole()),
                        customer.getUsers().getCreatedAt() != null
                                ? customer.getUsers().getCreatedAt().toLocalDate().format(formatter)
                                : ""
                );
            }
            writer.flush();
        }
    }

    private static void exportCustomerToExcel(HttpServletResponse response, List<Customers> customers, Integer month, Integer year) throws IOException {
        Workbook workbookCustomer = new XSSFWorkbook();
        Sheet sheet = workbookCustomer.createSheet("Customers");

        // Header
        // Tạo từng ô cell trong dòng 1 để ghi header
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Tên đăng nhập","Email","Số điện thoại","Họ và tên","Ngày sinh","Địa chỉ","Giới tính","Vai trò","Ngày tạo"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int rowIdx = 1;
        for (Customers customer : customers) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(customer.getUsers().getUsername());
            row.createCell(1).setCellValue(customer.getUsers().getEmail());
            row.createCell(2).setCellValue(customer.getUsers().getPhone());
            row.createCell(3).setCellValue(customer.getUsers().getFullName());
            row.createCell(4).setCellValue(customer.getUsers().getDateOfBirth() != null
                    ? customer.getUsers().getDateOfBirth().format(dateFormatter) : "");
            row.createCell(5).setCellValue(customer.getAddress());
            row.createCell(6).setCellValue(customer.getUsers().getGender() != null
                    ? customer.getUsers().getGender().equals("male") ? "Nam" : "Nữ"
                    : "");
            row.createCell(7).setCellValue(customer.getUsers().getRole());
            row.createCell(8).setCellValue(customer.getUsers().getCreatedAt() != null
                    ? customer.getUsers().getCreatedAt().toLocalDate().format(dateFormatter) : "");
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // file excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Content-Disposition tải file thay vì mở trực tiếp
        String fileName = String.format("customer_report_%02d_%d.xlsx", month, year);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        workbookCustomer.write(response.getOutputStream());
        workbookCustomer.close();
    }


    @Override
    public void exportStaff(HttpServletResponse response, ReportCreateRequest request, int id) {
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
            if("xlsx".equalsIgnoreCase(request.getReportType())) {
                exportStaffToExcel(response, filterStaffs, request.getMonth(), request.getYear());
            } else {
                exportStaffToCSV(response, filterStaffs, request.getMonth(), request.getYear());
            }
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

    private static void exportStaffToCSV(HttpServletResponse response, List<Staffs> staffs, int month, int year) throws IOException {
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
                        staff.getUsers().getGender() != null
                                ? staff.getUsers().getGender().equals("male") ? "Nam" : "Nữ"
                                : "",
                        escape(staff.getUsers().getRole()),
                        staff.getUsers().getCreatedAt() != null
                                ? staff.getUsers().getCreatedAt().toLocalDate().format(formatter)
                                : ""
                );
            }
            writer.flush();
        }
    }

    private static void exportStaffToExcel(HttpServletResponse response, List<Staffs> staffs, Integer month, Integer year) throws IOException {
        Workbook workbookStaff = new XSSFWorkbook();
        Sheet sheet = workbookStaff.createSheet("Staffs");

        // Header
        // Tạo từng ô cell trong dòng 1 để ghi header
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Tên đăng nhập","Email","Số điện thoại","Họ và tên","Ngày sinh","Ca làm việc","Giới tính","Vai trò","Ngày tạo"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int rowIdx = 1;
        for (Staffs staff : staffs) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(staff.getUsers().getUsername());
            row.createCell(1).setCellValue(staff.getUsers().getEmail());
            row.createCell(2).setCellValue(staff.getUsers().getPhone());
            row.createCell(3).setCellValue(staff.getUsers().getFullName());
            row.createCell(4).setCellValue(staff.getUsers().getDateOfBirth() != null
                    ? staff.getUsers().getDateOfBirth().format(dateFormatter) : "");
            row.createCell(5).setCellValue(staff.getWorkShift());
            row.createCell(6).setCellValue(staff.getUsers().getGender() != null
                    ? staff.getUsers().getGender().equals("male") ? "Nam" : "Nữ"
                    : "");
            row.createCell(7).setCellValue(staff.getUsers().getRole());
            row.createCell(8).setCellValue(staff.getUsers().getCreatedAt() != null
                    ? staff.getUsers().getCreatedAt().toLocalDate().format(dateFormatter) : "");
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // file excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Content-Disposition tải file thay vì mở trực tiếp
        String fileName = String.format("staff_report_%02d_%d.xlsx", month, year);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        workbookStaff.write(response.getOutputStream());
        workbookStaff.close();
    }

    @Override
    public void exportDoctor(HttpServletResponse response, ReportCreateRequest request, int id) {
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
            if ("xlsx".equalsIgnoreCase(request.getFileType())) {
                exportDoctorToExcel(response, filterDoctors, request.getMonth(), request.getYear());
            } else {
                exportDoctorToCSV(response, filterDoctors, request.getMonth(), request.getYear());
            }
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

    private static void exportDoctorToCSV(HttpServletResponse response, List<Doctors> doctors, int month, int year) throws IOException {
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
                        doctor.getUsers().getGender() != null
                                ? doctor.getUsers().getGender().equals("male") ? "Nam" : "Nữ"
                                : "",
                        escape(doctor.getUsers().getRole()),
                        doctor.getUsers().getCreatedAt() != null
                                ? doctor.getUsers().getCreatedAt().toLocalDate().format(formatter)
                                : ""
                );
            }
            writer.flush();
        }
    }

    private static void exportDoctorToExcel(HttpServletResponse response, List<Doctors> doctors, Integer month, Integer year) throws IOException {
        Workbook workbookDoctor = new XSSFWorkbook();
        Sheet sheet = workbookDoctor.createSheet("Doctors");

        // Header
        // Tạo từng ô cell trong dòng 1 để ghi header
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Tên đăng nhập","Email","Số điện thoại","Họ và tên","Ngày sinh","Giấy phép hành nghề","Năm kinh nghiệm","Giới tính","Vai trò","Ngày tạo"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int rowIdx = 1;
        for (Doctors doctor : doctors) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(doctor.getUsers().getUsername());
            row.createCell(1).setCellValue(doctor.getUsers().getEmail());
            row.createCell(2).setCellValue(doctor.getUsers().getPhone());
            row.createCell(3).setCellValue(doctor.getUsers().getFullName());
            row.createCell(4).setCellValue(doctor.getUsers().getDateOfBirth() != null
                    ? doctor.getUsers().getDateOfBirth().format(dateFormatter) : "");
            row.createCell(5).setCellValue(doctor.getLicenseNumber());
            row.createCell(6).setCellValue(doctor.getYearExperience());
            row.createCell(7).setCellValue(doctor.getUsers().getGender() != null
                    ? doctor.getUsers().getGender().equals("male") ? "Nam" : "Nữ"
                    : "");
            row.createCell(8).setCellValue(doctor.getUsers().getRole());
            row.createCell(9).setCellValue(doctor.getUsers().getCreatedAt() != null
                    ? doctor.getUsers().getCreatedAt().toLocalDate().format(dateFormatter) : "");
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // file excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Content-Disposition tải file thay vì mở trực tiếp
        String fileName = String.format("doctor_report_%02d_%d.xlsx", month, year);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        workbookDoctor.write(response.getOutputStream());
        workbookDoctor.close();
    }

    @Override
    public void exportAppointment(HttpServletResponse response, ReportCreateRequest request, int id) throws IOException {
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
        if("xlsx".equalsIgnoreCase(request.getFileType())){
            exportAppointmentToExcel(response, filterAppointments, request.getMonth(), request.getYear());
        } else {
            exportAppointmentToCSV(response, filterAppointments, request.getMonth(), request.getYear());
        }

        // Ghi báo cáo vào bảng report, tạo mới nếu ko có lỗi
        Reports report = new Reports();
        report.setManagers(manager);
        report.setReportType(request.getReportType());
        report.setCreatedAt(LocalDateTime.now());
        // Lưu thông tin vào bảng report
        reportRepository.save(report);
    }

    private static void exportAppointmentToCSV(HttpServletResponse response, List<Appointments> appointments, int month, int year) throws IOException {
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

    private static void exportAppointmentToExcel(HttpServletResponse response, List<Appointments> appointments, Integer month, Integer year) throws IOException {
        Workbook workbookAppointment = new XSSFWorkbook();
        Sheet sheet = workbookAppointment.createSheet("Appointments");

        // Header
        // Tạo từng ô cell trong dòng 1 để ghi header
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Khách hàng","Bác sĩ","Nhân viên","Hồ sơ y tế","Ngày hẹn","Giờ bắt đầu","Giờ kết thúc","Ẩn danh","Loại lịch hẹn"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        int rowIdx = 1;
        for (Appointments appointment : appointments) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(appointment.isAnonymous()
                    ? "Ẩn danh"
                    : appointment.getCustomers().getUsers().getFullName());
            row.createCell(1).setCellValue(appointment.getDoctors().getUsers().getFullName());
            row.createCell(2).setCellValue(appointment.getStaffs().getUsers().getFullName());
            row.createCell(3).setCellValue(appointment.getMedicalRecords().getDiagnosis());
            row.createCell(4).setCellValue(appointment.getSchedules().getWorkDate() != null
                    ? appointment.getSchedules().getWorkDate().format(dateFormatter)
                    : "");
            row.createCell(5).setCellValue(appointment.getStartTime() != null
                    ? appointment.getStartTime().format(timeFormatter)
                    : "");
            row.createCell(6).setCellValue(appointment.getEndTime() != null
                    ? appointment.getEndTime().format(timeFormatter)
                    : "");
            row.createCell(7).setCellValue(appointment.isAnonymous()
                    ? "Ẩn danh" : "Không ẩn danh");
            row.createCell(8).setCellValue(appointment.getAppointmentType().equals("Test HIV")
                    ? "Xét nghiệm HIV" : "Tư vấn");
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // file excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Content-Disposition tải file thay vì mở trực tiếp
        String fileName = String.format("appointment_report_%02d_%d.xlsx", month, year);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        workbookAppointment.write(response.getOutputStream());
        workbookAppointment.close();
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

