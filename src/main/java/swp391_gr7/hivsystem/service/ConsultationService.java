package swp391_gr7.hivsystem.service;

import java.util.List;
import swp391_gr7.hivsystem.model.Consultations;
import swp391_gr7.hivsystem.dto.request.ConsultationCreateRequest;

public interface ConsultationService {
    Consultations createConsultation(ConsultationCreateRequest request);
    Consultations getConsultationById(int id);
    List<Consultations> getConsultationsByCustomer(int customerId);
    List<Consultations> getConsultationsByDoctor(int doctorId);
    boolean deleteConsultation(int id);
    String getError();
}