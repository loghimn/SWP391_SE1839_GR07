package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.TreatmentPlans;
import swp391_gr7.hivsystem.repository.ArvRegimentRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.TreatmentPlansRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArvRegimentServiceImp implements ArvRegimentService {

    @Autowired
    private ArvRegimentRepository arvRegimentRepository;
    @Autowired
    private TreatmentPlansRepository treatmentPlansRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Boolean createArvRegiment(ArvRegimentCreateRequest request, int doctorId) {
        ArvRegiments arvRegiments = new ArvRegiments();
        arvRegiments.setName(request.getName());
        arvRegiments.setLevel(request.getLevel());
        arvRegiments.setDescription(request.getDescription());
        arvRegiments.setDoctor(doctorRepository.getDoctorsByDoctorId(doctorId));
        arvRegiments = arvRegimentRepository.save(arvRegiments);
        return arvRegiments != null;
    }

    @Override
    public List<ArvRegiments> getAllArvRegiments() {
        return arvRegimentRepository.findAll();
    }

    @Override
    public Optional<ArvRegiments> getArvRegimentById(int id) {
        return arvRegimentRepository.findById(id);
    }

    @Override
    public Boolean updateArvRegiment(int id, ArvRegimentCreateRequest request) {
        Optional<ArvRegiments> optional = arvRegimentRepository.findById(id);
        if (optional.isPresent()) {
            ArvRegiments arvRegiments = optional.get();
            arvRegiments.setName(request.getName());
            arvRegiments.setLevel(request.getLevel());
            arvRegiments.setDescription(request.getDescription());
            arvRegimentRepository.save(arvRegiments);
            return true;
        }
        return false;
    }

    @Override
    public List<ArvMedications> suggestArvMedication(int treatmentPlanId) {
        TreatmentPlans treatmentPlans = treatmentPlansRepository.findById(treatmentPlanId).orElse(null);
        if (treatmentPlans == null) {
            return Collections.emptyList();
        }
        ArvRegiments arvRegiments = treatmentPlans.getArvReqimentID();
        if (arvRegiments == null) {
            return Collections.emptyList();
        }
        return arvRegiments.getMedications();
    }

    @Override
    public List<ArvRegiments> getArvRegimentsByDoctorId(int doctorId) {
        return arvRegimentRepository.findArvRegimentsByDoctor_DoctorId(doctorId);
    }

    @Override
    public List<ArvRegiments> getMyArvRegiments(int doctorId) {
        return arvRegimentRepository.findArvRegimentsByDoctor_DoctorId(doctorId);
    }
}