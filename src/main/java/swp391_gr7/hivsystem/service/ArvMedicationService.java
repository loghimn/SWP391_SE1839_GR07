package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ArvMedicationCreateRequest;
import swp391_gr7.hivsystem.dto.response.ArvMedicationResponse;
import swp391_gr7.hivsystem.model.ArvMedications;

import java.util.List;

public interface ArvMedicationService {
    boolean addArvMedication(ArvMedicationCreateRequest request, int doctorId);

    boolean updateArvMedication(ArvMedicationCreateRequest request, int id);

    boolean deleteArvMedication(int code);

//    List<ArvMedications> showAllListMedication();

    List<ArvMedications> getMedicationByArvRegimentId(int arvRegimentId);

    List<ArvMedicationResponse> showAllListMedication();
}