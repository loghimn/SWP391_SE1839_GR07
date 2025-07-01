package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.dto.response.CustomerReponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
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
    private SchedulesRepository schedulesRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    private final String error = "";

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

        Customers customers = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

        Optional<Doctors> doctorsOpt = doctorRepository.findDoctorByFullName(request.getDoctorName());
        Doctors doctors = null;
        if (doctorsOpt.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND);
        } else {
            doctors = doctorsOpt.get();
        }

        Staffs staffs = staffService.findStaffHasLeastAppointment();

        List<Schedules> schedulesList = schedulesRepository.findByDoctors_DoctorId(doctors.getDoctorId());

        if (schedulesList.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }


        Schedules schedules = null;
        for (Schedules s : schedulesList) {
            if (s.getWorkDate() == request.getAppointmentTime()) {
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

        // Check if appointment date matches doctor's working date
        if (!schedules.getWorkDate().equals(request.getAppointmentTime())) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_WORKING);
        }

        // Check for duplicate appointment time for customer
        for (Appointments a : customers.getAppointments()) {
            if (request.getAppointmentTime().equals(a.getAppointmentTime()) && a.isStatus()) {
                throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_CUSTOMER);
            }
        }

        // Check for duplicate appointment time for doctor
        for (Appointments a : doctors.getAppointments()) {
            if (request.getAppointmentTime().equals(a.getAppointmentTime()) && a.isStatus()) {
                throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_DOCTOR);
            }
        }

        Appointments appointments = new Appointments();
        appointments.setCustomers(customers);
        appointments.setDoctors(doctors);
        appointments.setStaffs(staffs);
        appointments.setMedicalRecords(medicalRecord);
        appointments.setAppointmentTime(request.getAppointmentTime());
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
        Appointments appointments = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        if(!appointments.isStatus()){
            throw new AppException(ErrorCode.APPOINTMENT_ALREADY_IS_NOT_ACTIVE);
        }

        Customers customers = customerRepository.findById(appointments.getCustomers().getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_CUSTOMER_NOT_FOUND));

        Doctors doctors = doctorRepository.findDoctorByFullName(request.getDoctorName())
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_FOUND));

        Staffs staffs = staffService.findStaffHasLeastAppointment();

        List<Schedules> schedulesList = schedulesRepository.findByDoctors_DoctorId(doctors.getDoctorId());

        if (schedulesList.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }


        Schedules schedules = null;
        for (Schedules s : schedulesList) {
            if (s.getWorkDate() == request.getAppointmentTime()) {
                schedules = s;
                break;
            }

        }
        if (schedules == null) {
            throw new AppException(ErrorCode.APPOINTMENT_SCHEDULE_NOT_FOUND);
        }

        MedicalRecords medicalRecord = medicalRecordRepository.findByCustomers(customers)
                .orElseThrow(()-> new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND_WITH_CUSTOMER));

        if (!schedules.getWorkDate().equals(request.getAppointmentTime())) {
            throw new AppException(ErrorCode.APPOINTMENT_DOCTOR_NOT_WORKING);
        }

        // Check for duplicate appointment time for customer (exclude current)
        for (Appointments a : customers.getAppointments()) {
            if (a.getAppointmentId() != id && request.getAppointmentTime().equals(a.getAppointmentTime())) {
                throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_CUSTOMER);
            }
        }

        // Check for duplicate appointment time for doctor (exclude current)
        for (Appointments a : doctors.getAppointments()) {
            if (a.getAppointmentId() != id && request.getAppointmentTime().equals(a.getAppointmentTime())) {
                throw new AppException(ErrorCode.APPOINTMENT_DUPLICATE_DOCTOR);
            }
        }

        appointments.setDoctors(doctors);
        appointments.setStaffs(staffs);
        appointments.setMedicalRecords(medicalRecord);
        appointments.setAppointmentTime(request.getAppointmentTime());
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
}