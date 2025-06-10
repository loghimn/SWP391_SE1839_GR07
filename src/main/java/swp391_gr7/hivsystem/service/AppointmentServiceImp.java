package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.Staff;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    CustomerRepository customerRepository;
    public static String error = "";
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;

    @Override
    public Appointment addAppointment(AppointmentCreateRequest request) {
         // Khởi tạo chuỗi lỗi rỗng
         error = "";
        // Xử lý customer
        Optional<Customer> customerOpt = customerRepository.findCustomersByMail(request.getCustomerMail());
        Customer customer = null;
        if (customerOpt.isEmpty()) {
            error += "Customer not found with mail. ";
        } else {
            customer = customerOpt.get();
            for (Appointment appointmentCheck : customer.getAppointments()) {
                if (request.getAppointmentTime().equals(appointmentCheck.getAppointmentTime())) {
                    error += "Appointment time with this customer already exists. ";
                    break;
                }
            }
        }

        // Xử lý doctor
        Optional<Doctor> doctorOpt = doctorRepository.findDoctorByUser_Username(request.getDoctorsName());
        Doctor doctor = null;
        if (doctorOpt.isEmpty()) {
            error += "Doctor not found with name. ";
        } else {
            doctor = doctorOpt.get();
            for (Appointment appointmentCheck : doctor.getAppointments()) {
                if (request.getAppointmentTime().equals(appointmentCheck.getAppointmentTime())) {
                    error += "Appointment time with this doctor already exists. ";
                    break;
                }
            }
        }
        System.out.println(error);
        // Nếu có lỗi, in ra và trả null
        if (!error.isEmpty()) {
            System.out.println(error);
            return null;
        }
        // Tạo mới appointment nếu không có lỗi
        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus(request.isStatus());
        appointment.setAnonymous(request.isAnonymous());
        Staff staff = staffService.findStaffHasLeastAppointment();
        appointment.setStaff(staff);
        return appointmentRepository.save(appointment);
    }





    //Tra full lít
    public List<Appointment> getAllAppointmentsFullInfor(){
        List<Appointment> list = appointmentRepository.findAll();
        return list;
    }
//Tra ve list ma anonymus = true hay an danh
    @Override
    public List<Appointment> getAllAppointmentsEcceptAnonymous() {
        List<Appointment> list = appointmentRepository.findAllByAnonymous(true);
        return list;
    }


}
