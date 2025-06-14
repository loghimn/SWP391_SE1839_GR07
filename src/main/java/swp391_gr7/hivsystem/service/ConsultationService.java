package swp391_gr7.hivsystem.service;

import java.util.List;
import swp391_gr7.hivsystem.model.Consultations;
import swp391_gr7.hivsystem.dto.request.ConsultationCreateRequest;

public interface ConsultationService {
    Consultations createConsultation(ConsultationCreateRequest request);
    Consultations getConsultationById(Long id);
    List<Consultations> getConsultationsByCustomer(Long customerId);
    List<Consultations> getConsultationsByDoctor(Long doctorId);
    boolean deleteConsultation(Long id);
    String getError();
}