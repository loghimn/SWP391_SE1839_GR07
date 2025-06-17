package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.model.TreatmentPlans;

public interface TreatmentPlanService {
    Boolean createTreatmentPlan(TreatmentPlansCreateRequest request);
}
