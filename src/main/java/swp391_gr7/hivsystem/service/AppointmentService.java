package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.Customer;

@Service
public interface AppointmentService {
    public Appointment addAppointment(AppointmentCreateRequest request);
}
