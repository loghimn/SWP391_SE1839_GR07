package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.ArvRegiments;

import java.util.List;

public interface ArvRegimentService {
    Boolean createArvRegiment(ArvRegimentCreateRequest request);
    //public ArvRegiments suggestArvMedical(int testResultId);

    List<ArvMedications> suggestArvMedication(int treatmentPlanId);
}
