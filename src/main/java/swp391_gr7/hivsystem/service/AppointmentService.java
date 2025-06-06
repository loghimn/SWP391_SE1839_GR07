package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.model.Appointment;

import java.util.List;

@Service
public interface AppointmentService {
    public Appointment addAppointment(AppointmentCreateRequest request);
    public List<Appointment> getAllAppointmentsFullInfor();
    public List<Appointment> getAllAppointmentsEcceptAnonymous();
}
