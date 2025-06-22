package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.Appointments;

import java.util.List;

@Service
public interface AppointmentService {
    //public String error = null;
    public Appointments addAppointment(int id, AppointmentCreateRequest request);
    public List<Appointments> getAllAppointmentsFullInfor();
    public List<Appointments> getAllAppointmentsEcceptAnonymous();
    String getErrorMessage();
}
