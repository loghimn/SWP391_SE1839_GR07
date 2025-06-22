package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class ArvRegimentServiceImp implements ArvRegimentService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ArvRegimentRepository arvRegimentRepository;
    @Autowired
    TestResultRepository testResultRepository;
    @Autowired
    ArvMedicationsRepository arvMedicationRepository;
    @Autowired
    TreatmentPlansRepository treatmentPlansRepository;
    @Autowired
    TreatmentPlanService treatmentPlanService;

    @Override
    public Boolean createArvRegiment(ArvRegimentCreateRequest request) {
        ArvRegiments arvRegiments = new ArvRegiments();
        arvRegiments.setDescription(request.getDescription());
        arvRegiments.setLevel(Integer.valueOf(request.getLevel()));
        arvRegiments = arvRegimentRepository.save(arvRegiments);
        return arvRegiments != null;
    }

    public List<ArvMedications> suggestArvMedication(int treatmentPlanId) {
        TreatmentPlans treatmentPlans = treatmentPlansRepository.findById(treatmentPlanId).get();

        //System.out.println(customers.getAddress());
        ArvRegiments arvRegiments = treatmentPlans.getArvReqimentID();
        if (arvRegiments == null) {
            return Collections.emptyList();
        }

        return arvRegiments.getMedications();
    }


}
