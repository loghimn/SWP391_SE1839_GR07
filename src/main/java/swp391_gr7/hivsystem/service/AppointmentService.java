package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.AppointmentCreateRequest;
import swp391_gr7.hivsystem.dto.response.CustomerReponse;
import swp391_gr7.hivsystem.model.Appointments;

import java.util.List;

@Service
public interface AppointmentService {
    //public String error = null;
    Appointments addAppointment(int id, AppointmentCreateRequest request);

    List<Appointments> getAllAppointmentsFullInfor();

    List<Appointments> getAllAppointmentsEcceptAnonymous();

    List<Appointments> getAllAppointmentsAnonymous();

    String getErrorMessage();

    Appointments updateAppointment(int id, AppointmentCreateRequest request);

    Appointments deleteAppointment(int id);

    Appointments getAppointmentById(int id);

    List<Appointments> getAppointmentsByCustomerId(int customerId);

    List<Appointments> getCustomerAppointment(int currentCustomerId);

    CustomerReponse getCustomerAppointmentInDocorView(int appointmentId);

    List<Appointments> getMyAppointmentsCus(int customerId);

    List<Appointments> getMyAppointmentsDoc(int doctorId);
}
