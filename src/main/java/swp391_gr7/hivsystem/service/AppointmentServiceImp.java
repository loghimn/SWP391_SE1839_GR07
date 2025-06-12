package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Staffs;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;

import java.util.List;
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
    public Appointments addAppointment(AppointmentCreateRequest request) {
         // Khởi tạo chuỗi lỗi rỗng
         error = "";
        // Xử lý customer
        Optional<Customers> customerOpt = customerRepository.findCustomersByMail(request.getCustomerMail());
        Customers customers = null;
        if (customerOpt.isEmpty()) {
            error += "Customer not found with mail. ";
        } else {
            customers = customerOpt.get();
            for (Appointments appointmentsCheck : customers.getAppointments()) {
                if (request.getAppointmentTime().equals(appointmentsCheck.getAppointmentTime())) {
                    error += "Appointment time with this customer already exists. ";
                    break;
                }
            }
        }

        // Xử lý doctor
        Optional<Doctors> doctorOpt = doctorRepository.findDoctorByUser_Username(request.getDoctorsName());
        Doctors doctors = null;
        if (doctorOpt.isEmpty()) {
            error += "Doctor not found with name. ";
        } else {
            doctors = doctorOpt.get();
            for (Appointments appointmentsCheck : doctors.getAppointments()) {
                if (request.getAppointmentTime().equals(appointmentsCheck.getAppointmentTime())) {
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
        Appointments appointments = new Appointments();
        appointments.setCustomers(customers);
        appointments.setDoctors(doctors);
        appointments.setAppointmentTime(request.getAppointmentTime());
        appointments.setStatus(request.isStatus());
        appointments.setAnonymous(request.isAnonymous());
        Staffs staffs = staffService.findStaffHasLeastAppointment();
        appointments.setStaffs(staffs);
        return appointmentRepository.save(appointments);
    }





    //Tra full lít
    public List<Appointments> getAllAppointmentsFullInfor(){
        List<Appointments> list = appointmentRepository.findAll();
        return list;
    }
//Tra ve list ma anonymus = true hay an danh
    @Override
    public List<Appointments> getAllAppointmentsEcceptAnonymous() {
        List<Appointments> list = appointmentRepository.findAllByAnonymous(true);
        return list;
    }


}
