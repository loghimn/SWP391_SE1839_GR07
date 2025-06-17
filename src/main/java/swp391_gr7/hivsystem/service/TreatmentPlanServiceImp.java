package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.model.TreatmentPlans;
import swp391_gr7.hivsystem.repository.ArvRegimentRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.TreatmentPlansRepository;

@Service
public class TreatmentPlanServiceImp implements TreatmentPlanService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ArvRegimentRepository arvRegimentRepository;
    @Autowired
    TreatmentPlansRepository treatmentPlansRepository;
    @Override
    public Boolean createTreatmentPlan(TreatmentPlansCreateRequest request) {
        TreatmentPlans plan = new TreatmentPlans();
        plan.setDoctors(doctorRepository.getDoctorsByDoctorId(request.getDoctorId()));
        plan.setPlanDescription(request.getTreatmentPlanDescription());
        System.out.println(arvRegimentRepository.findArvRegimentsByArvRegimentID(request.getArvRegimentId()));
        plan.setArvReqimentID(arvRegimentRepository.findArvRegimentsByArvRegimentID(request.getArvRegimentId()));
        plan = treatmentPlansRepository.save(plan);
        if (plan != null) {
            return true;
        }
        return false;
    }
}
