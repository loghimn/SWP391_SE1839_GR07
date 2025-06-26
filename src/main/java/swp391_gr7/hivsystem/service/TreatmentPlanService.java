package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.model.TreatmentPlans;

import java.util.List;

public interface TreatmentPlanService {
    Boolean createTreatmentPlan(int doctorId, TreatmentPlansCreateRequest request);

    TreatmentPlans getTreatmentPlanById(int id);

    TreatmentPlans updateTreatmentPlan(int id, TreatmentPlansCreateRequest request);

    List<TreatmentPlans> getAll();

    List<TreatmentPlans> getMyTreatmentPlant(int doctorId);
}
