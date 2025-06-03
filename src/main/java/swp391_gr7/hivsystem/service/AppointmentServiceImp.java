package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;

import java.util.List;


@Service
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Appointment addAppointment(AppointmentCreateRequest request) {
        Appointment appointment = new Appointment();

        // Lấy customer (nên check Optional)
        var customer = customerRepository.findCustomersByMail(request.getCustomerMail());
        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found with mail: " + request.getCustomerMail());
        }
        // Lấy danh sách doctor, rồi lấy doctor đầu tiên (nếu có)
        var dortor = doctorRepository.findDoctorByUsername(request.getDoctorsName());
        if (dortor.isEmpty()) {
            throw new RuntimeException("Doctor not found with name: " + request.getDoctorsName());
        }
        appointment.setCustomer(customer.get());
        appointment.setDoctor(dortor.get());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus(request.isStatus());
        appointment.setAnonymous(true);

        return appointmentRepository.save(appointment);
    }

}
