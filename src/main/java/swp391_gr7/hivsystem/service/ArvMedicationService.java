package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ArvMedicationCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;

import java.util.List;

public interface ArvMedicationService {
    boolean addArvMedication(ArvMedicationCreateRequest request, int doctorId);

    boolean updateArvMedication(ArvMedicationCreateRequest request, String code);

    boolean deleteArvMedication(int code);

    List<ArvMedications> showAllListMedication();

    List<ArvMedications> getMedicationByArvRegimentId(int arvRegimentId);


}