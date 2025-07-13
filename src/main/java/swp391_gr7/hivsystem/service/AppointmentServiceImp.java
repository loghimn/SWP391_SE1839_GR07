package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.dto.response.CustomerReponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;
    @Autowired
    private SchedulesRepository schedulesRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    private final String error = "";
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Appointments addAppointment(AppointmentCreateRequest request) {
//         // Khởi tạo chuỗi lỗi rỗng
//         error = "";
//        // Xử lý customer
//        Optional<Customers> customerOpt = customerRepository.findCustomersByMail(request.getCustomerMail());
//        Customers customers = null;
//        if (customerOpt.isEmpty()) {
//            error += "Customer not found with mail. ";
//        } else {
//            customers = customerOpt.get();
//            for (Appointments appointmentsCheck : customers.getAppointments()) {
//                if (request.getAppointmentTime().equals(appointmentsCheck.getAppointmentTime())) {
//                    error += "Appointment time with this customer already exists. ";
//                    break;
//                }
//            }
//        }
//
//        // Xử lý doctor
//        Optional<Doctors> doctorOpt = doctorRepository.findDoctorByUser_Username(request.getDoctorsName());
//        Doctors doctors = null;
//        if (doctorOpt.isEmpty()) {
//            error += "Doctor not found with name. ";
//        } else {
//            doctors = doctorOpt.get();
//            for (Appointments appointmentsCheck : doctors.getAppointments()) {
//                if (request.getAppointmentTime().equals(appointmentsCheck.getAppointmentTime())) {
//                    error += "Appointment time with this doctor already exists. ";
//                    break;
//                }
//            }
//        }
//        System.out.println(error);
//        // Nếu có lỗi, in ra và trả null
//        if (!error.isEmpty()) {
//            System.out.println(error);
//            return null;
//        }
//        // Tạo mới appointment nếu không có lỗi
//        Appointments appointments = new Appointments();
//        appointments.setCustomers(customers);
//        appointments.setDoctors(doctors);
//        appointments.setAppointmentTime(request.getAppointmentTime());
//        appointments.setStatus(request.isStatus());
//        appointments.setAnonymous(request.isAnonymous());
//        Staffs staffs = staffService.findStaffHasLeastAppointment();
//        appointments.setStaffs(staffs);
//        return appointmentRepository.save(appointments);
//    }

    @Override
    public Appointments addAppointment(int id, AppointmentCreateRequest request) {
        if (request.getStartTime().toLocalTime().isBefore(LocalTime.of(7, 59)) ||
                request.getStartTime().toLocalTime().isAfter(LocalTime.of(16, 1)) ||
                request.getStartTime().toLocalTime().getHour() == 9 ||
                request.getStartTime().toLocalTime().getHour() == 11 ||
                request.getStartTime().toLocalTime().getHour() == 12 ||
                request.getStartTime().toLocalTime().getHour() == 13 ||
                request.getStartTime().toLocalTime().getHour() == 15) {
            throw new AppException(ErrorCode.TIME_APPOINTMENT_NOT_FOUND);
        }


        Customers customers = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

        Optional<Doctors> doctorsOpt = doctorRepository.findDoctorByFullName(request.getDoctorName());
        Doctors doctors = null;
        if (doctorsOpt.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND);
        } else {
            doctors = doctorsOpt.get();
        }
        int workshift = request.getStartTime().getHour() > 12 ? 2 : 1;
        Staffs staffs = staffService.findStaffHasLeastAppointment(workshift);

        List<Schedules> schedulesList = schedulesRepository.findByDoctors_DoctorId(doctors.getDoctorId());
        LocalDateTime endTime = request.getStartTime().plusHours(2);
        if (schedulesList.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }


        Schedules schedules = null;
        for (Schedules s : schedulesList) {
            if (s.getWorkDate().equals(request.getStartTime().toLocalDate())) {
                schedules = s;
                break;
            }
        }
        if (schedules == null) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }

        MedicalRecords medicalRecord = medicalRecordRepository.findByCustomers(customers)
                .orElseThrow(() -> new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND_WITH_CUSTOMER));

//        if (customers == null) {
//            throw new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND);
//        }
        if (doctors == null) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND);
        }
        if (schedules == null) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }
        // LocalDateTime endTime = request.getStartTime().plusHours(2);
        // Check if appointment date matches doctor's working date
        if (!schedules.getWorkDate().equals(request.getStartTime().toLocalDate())) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_WORKING);
        }

        // Check for duplicate appointment time for customer
        for (Appointments a : customers.getAppointments()) {
            if (a.isStatus()) {
                Duration diff = Duration.between(a.getStartTime(), request.getStartTime()).abs();
                if (diff.toHours() < 2) {
                    throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_CUSTOMER);
                }
            }
        }


        // Check for duplicate appointment time for doctor
        for (Appointments a : doctors.getAppointments()) {
            if (a.isStatus()) {
                Duration diff = Duration.between(a.getStartTime(), request.getStartTime()).abs();
                if (diff.toHours() < 2) {
                    throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_CUSTOMER);
                }
            }
        }

        Appointments appointments = new Appointments();
        appointments.setCustomers(customers);
        appointments.setDoctors(doctors);
        appointments.setStaffs(staffs);
        appointments.setMedicalRecords(medicalRecord);
        appointments.setStartTime(request.getStartTime());
        appointments.setEndTime(endTime);
        appointments.setStatus(true);
        appointments.setAnonymous(request.isAnonymous());
        appointments.setAppointmentType(request.getAppointmentType());
        appointments.setSchedules(schedules);

        return appointmentRepository.save(appointments);
    }

    @Override
    public List<Appointments> getAllAppointmentsFullInfor() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointments> getAllAppointmentsEcceptAnonymous() {
        return appointmentRepository.findAllByAnonymous(false);
    }

    @Override
    public List<Appointments> getAllAppointmentsAnonymous() {
        return appointmentRepository.findAllByAnonymous(true);
    }


    @Override
    public String getErrorMessage() {
        return error;
    }

    @Override
    public Appointments updateAppointment(int id, AppointmentCreateRequest request) {

        if (request.getStartTime().toLocalTime().isBefore(LocalTime.of(7, 59)) ||
                request.getStartTime().toLocalTime().isAfter(LocalTime.of(16, 1)) ||
                request.getStartTime().toLocalTime().getHour() == 9 ||
                request.getStartTime().toLocalTime().getHour() == 11 ||
                request.getStartTime().toLocalTime().getHour() == 12 ||
                request.getStartTime().toLocalTime().getHour() == 13 ||
                request.getStartTime().toLocalTime().getHour() == 15) {
            throw new AppException(ErrorCode.TIME_APPOINTMENT_NOT_FOUND);
        }
        Appointments appointments = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        if (!appointments.isStatus()) {
            throw new AppException(ErrorCode.APPOINTMENT_ALREADY_IS_NOT_ACTIVE);
        }

        Customers customers = customerRepository.findById(appointments.getCustomers().getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND));

        Doctors doctors = doctorRepository.findDoctorByFullName(request.getDoctorName())
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND));

        int workshift = request.getStartTime().getHour() > 12 ? 2 : 1;
        Staffs staffs = staffService.findStaffHasLeastAppointment(workshift);

        List<Schedules> schedulesList = schedulesRepository.findByDoctors_DoctorId(doctors.getDoctorId());
        LocalDateTime endTime = request.getStartTime().plusHours(2);
        if (schedulesList.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }


        Schedules schedules = null;
        for (Schedules s : schedulesList) {
            if (s.getWorkDate().equals(request.getStartTime().toLocalDate())) {
                schedules = s;
                break;
            }
        }
        if (schedules == null) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }
        MedicalRecords medicalRecord = medicalRecordRepository.findByCustomers(customers)
                .orElseThrow(() -> new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND_WITH_CUSTOMER));

        if (!schedules.getWorkDate().equals(request.getStartTime().toLocalDate())) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_WORKING);
        }

//        // Check if the appointment already exists
//        if (appointmentRepository.existsByStartTimeAndDoctors_DoctorIdAndStatus(request.getStartTime(), doctors.getDoctorId(), true)) {
//            throw new AppException(ErrorCode.APPOINTMENT_ALREADY_EXISTS);
//        }

        if (!appointments.getStartTime().equals(request.getStartTime())) {
            // Check for duplicate appointment time for customer
            for (Appointments a : customers.getAppointments()) {
                if (a.isStatus()) {
                    Duration diff = Duration.between(a.getStartTime(), request.getStartTime()).abs();
                    if (diff.toHours() < 2) {
                        throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_CUSTOMER);
                    }
                }
            }


            // Check for duplicate appointment time for doctor
            for (Appointments a : doctors.getAppointments()) {
                if (a.isStatus()) {
                    Duration diff = Duration.between(a.getStartTime(), request.getStartTime()).abs();
                    if (diff.toHours() < 2) {
                        throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_DOCTOR);
                    }
                }
            }
        }

//        Appointments appointments = new Appointments();
        appointments.setCustomers(customers);
        appointments.setDoctors(doctors);
        appointments.setStaffs(staffs);
        appointments.setMedicalRecords(medicalRecord);
        appointments.setStartTime(request.getStartTime());
        appointments.setEndTime(endTime);
        appointments.setStatus(true);
        appointments.setAnonymous(request.isAnonymous());
        appointments.setAppointmentType(request.getAppointmentType());
        appointments.setSchedules(schedules);


        return appointmentRepository.save(appointments);
    }

    @Override
    public Appointments deleteAppointment(int id) {
        Appointments appointments = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        // Check if the appointment is already deleted
        if (!appointments.isStatus()) {
            throw new AppException(ErrorCode.APPOINTMENT_ALREADY_DELETED);
        }

        // Set status to false to mark as deleted
        appointments.setStatus(false);

        return appointmentRepository.save(appointments);
    }

    @Override
    public Appointments getAppointmentById(int id) {
        Appointments appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        if (appointment != null && appointment.isStatus() && !appointment.isAnonymous()) {
            return appointment;
        } else if (appointment == null || !appointment.isStatus() || appointment.isAnonymous()) {
            // If the appointment is anonymous, return null
            return null;
        }
        return null;
    }

    @Override
    public List<Appointments> getAppointmentsByCustomerId(int customerId) {
        Customers customers = customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND));
        return appointmentRepository.findByCustomers_CustomerId(customerId);
    }

    @Override
    public List<Appointments> getCustomerAppointment(int currentCustomerId) {
        Customers customers = customerRepository.findById(currentCustomerId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND));

        // Check if the customer has any appointments
        if (customers.getAppointments().isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND);
        }
        return appointmentRepository.findByCustomers_CustomerId(currentCustomerId);
    }

    @Override
    public CustomerReponse getCustomerAppointmentInDocorView(int appointmentId) {
        Appointments appointments = getAppointmentById(appointmentId);
        if (appointments == null) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        } else {
            Customers customers = appointments.getCustomers();
            CustomerReponse customerReponse = new CustomerReponse();
            customerReponse.setFullName(customers.getUsers().getFullName());
            customerReponse.setEmail(customers.getUsers().getEmail());
            customerReponse.setGender(customers.getUsers().getGender());
            customerReponse.setPhone(customers.getUsers().getPhone());
            customerReponse.setAddress(customers.getAddress());
            return customerReponse;
        }

    }

    @Override
    public List<Appointments> getMyAppointmentsCus(int customerId) {
        Customers customers = customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND));
        return appointmentRepository.findByCustomers_CustomerId(customers.getCustomerId());
    }

    @Override
    public List<Appointments> getMyAppointmentsDoc(int doctorId) {
        Doctors doctors = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND));
        return appointmentRepository.findByDoctors_DoctorId(doctors.getDoctorId());
    }

    @Override
    public List<Appointments> getMyAppointmentsConsultationDoc(int doctorId) {
        Doctors doctors = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND));
        return appointmentRepository.findByDoctors_DoctorIdAndAppointmentType(doctors.getDoctorId(), "Consultation");
    }

    @Override
    public List<Appointments> getMyAppointmentsTestHIVDoc(int doctorId) {
        Doctors doctors = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND));
        return appointmentRepository.findByDoctors_DoctorIdAndAppointmentType(doctors.getDoctorId(), "Test HIV");
    }

    @Override
    public List<Appointments> getAppointmentsHaveTypeTestHIVAndActive() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Doctors doctor = doctorRepository.findByUsers_UserId(user.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
        return appointmentRepository.findByDoctorsAndAppointmentTypeAndStatus(doctor, "Test HIV", true);
    }

    @Override
    public List<Appointments> getStaffAppointmentsHaveTypeTestHIVAndActive() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Staffs staff = staffRepository.findByUsers_UserId(user.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));
        return appointmentRepository.findByStaffsAndAppointmentTypeAndStatus(staff, "Test HIV", true);
    }


    @Override
    public List<Appointments> getAppointmentsHaveTypeConsultationAndActive() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Doctors doctor = doctorRepository.findByUsers_UserId(user.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
        return appointmentRepository.findByDoctorsAndAppointmentTypeAndStatus(doctor, "Consultation", true);
    }

    @Override
    public List<Appointments> getAppointmentsByDay(LocalDate date) {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Users user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Doctors doctor = doctorRepository.findByUsers_UserId(user.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));

        LocalDateTime startOfDay = date.atStartOfDay();               // 00:00
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);          // 23:59:59.999...

        return appointmentRepository.findAppointmentsByDoctorsAndDateRange(doctor, startOfDay, endOfDay);
    }
}