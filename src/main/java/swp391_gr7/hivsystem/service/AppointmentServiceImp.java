package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

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
    private ScheduleRepository scheduleRepository;

    private String error = "";

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
    public Appointments addAppointment(AppointmentCreateRequest request) {
        error = "";

        Customers customers = customerRepository.findById((long) request.getCustomerId()).orElse(null);
        Doctors doctors = doctorRepository.findById((long) request.getDoctorId()).orElse(null);
        Staffs staffs = staffRepository.findById((long) request.getStaffId()).orElse(staffService.findStaffHasLeastAppointment());
        Schedules schedules = scheduleRepository.findById(request.getScheduleId()).orElse(null);

        if (customers == null || doctors == null || schedules == null) {
            error += "Invalid customer, doctor, or schedule. ";
            return null;
        }

        // Check if appointment date matches doctor's working date
        if (!schedules.getWorkDate().equals(request.getAppointmentTime())) {
            error += "Doctor is not working on the selected appointment day. ";
        }

        // Check for duplicate appointment time for customer
        for (Appointments a : customers.getAppointments()) {
            if (request.getAppointmentTime().equals(a.getAppointmentTime())) {
                error += "Appointment time with this customer already exists. ";
                break;
            }
        }

        // Check for duplicate appointment time for doctor
        for (Appointments a : doctors.getAppointments()) {
            if (request.getAppointmentTime().equals(a.getAppointmentTime())) {
                error += "Appointment time with this doctor already exists. ";
                break;
            }
        }

        if (!error.isEmpty()) {
            return null;
        }

        Appointments appointments = new Appointments();
        appointments.setCustomers(customers);
        appointments.setDoctors(doctors);
        appointments.setStaffs(staffs);
        appointments.setAppointmentTime(request.getAppointmentTime());
        appointments.setStatus(request.isStatus());
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
    public String getErrorMessage() {
        return error;
    }


}
