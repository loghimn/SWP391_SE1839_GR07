package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
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


    @Override
    public Consultations createConsultation(ConsultationCreateRequest request) {

        Appointments appointment = appointmentRepository.findByAppointmentId((Integer) request.getAppointmentId())
                .orElseThrow(() -> new AppException(ErrorCode.CONSULTATION_APPOINTMENT_NOT_FOUND));

        if (!"Consultation".equals(appointment.getAppointmentType())) {
            throw new AppException(ErrorCode.CONSULTATION_INVALID_APPOINTMENT_TYPE);
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
        return consultationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CONSULTATION_NOT_FOUND_BY_ID));
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
    public Consultations updateConsultation(int id, ConsultationCreateRequest request) {
        Consultations consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CONSULTATION_NOT_FOUND_BY_ID));

        if (request.getNotes() != null) {
            consultation.setNotes(request.getNotes());
        }

        return consultationRepository.save(consultation);
    }

}