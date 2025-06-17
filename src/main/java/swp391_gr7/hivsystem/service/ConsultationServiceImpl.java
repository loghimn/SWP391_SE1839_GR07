package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;
import swp391_gr7.hivsystem.dto.request.ConsultationCreateRequest;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    private String error = "";

    @Override
    public Consultations createConsultation(ConsultationCreateRequest request) {
        error = "";

        Appointments appointment = appointmentRepository.findByAppointmentId((Integer) request.getAppointmentId())
                .orElse(null);

        if (appointment == null) {
            error = "Appointment not found";
            return null;
        }

        if (!"Consultation".equals(appointment.getAppointmentType())) {
            error = "Appointment is not for consultation";
            return null;
        }

        Consultations consultation = new Consultations();
        consultation.setAppointments(appointment);
        consultation.setDoctors(appointment.getDoctors());
        consultation.setCustomers(appointment.getCustomers());
        consultation.setConsultationDate(LocalDate.now());
        consultation.setNotes(request.getNotes());

        return consultationRepository.save(consultation);
    }

    @Override
    public Consultations getConsultationById(int id) {
        return consultationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Consultations> getConsultationsByCustomer(int customerId) {
        return consultationRepository.findByCustomers_CustomerId(customerId);
    }

    @Override
    public List<Consultations> getConsultationsByDoctor(int doctorId) {
        return consultationRepository.findByDoctors_DoctorId(doctorId);
    }

    @Override
    public boolean deleteConsultation(int id) {
        if (consultationRepository.existsById(id)) {
            consultationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String getError() {
        return error;
    }
}