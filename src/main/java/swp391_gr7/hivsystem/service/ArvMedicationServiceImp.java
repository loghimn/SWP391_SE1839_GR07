package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ArvMedicationCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.repository.ArvMedicationsRepository;
import swp391_gr7.hivsystem.repository.ArvRegimentRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;

import java.util.List;

@Service
public class ArvMedicationServiceImp implements ArvMedicationService {
    @Autowired
    ArvMedicationsRepository arvMedicationsRepository;
    @Autowired
    ArvRegimentRepository arvRegimentRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public boolean addArvMedication(ArvMedicationCreateRequest request, int doctorId) {
        ArvMedications arvMedications = new ArvMedications();
        arvMedications.setCode(request.getCode());
        arvMedications.setName(request.getName());
        arvMedications.setForm(request.getForm());
        arvMedications.setStrength(request.getStrength());
        arvMedications.setManufacturer(request.getManufacturer());
        arvMedications.setDescription(request.getDescription());
        arvMedications.setActive(true);
        arvMedications.setArvRegiment(arvRegimentRepository.findArvRegimentsByNameContaining(request.getArvRegimentName()));
        Doctors doctor = doctorRepository.getDoctorsByDoctorId(doctorId);
        arvMedications.setDoctor(doctor);
        arvMedicationsRepository.save(arvMedications);
        return true;
    }

    @Override
    public boolean updateArvMedication(ArvMedicationCreateRequest request, String code) {
        ArvMedications arvMedications = arvMedicationsRepository.getArvMedicationsByCode(code);
        arvMedications.setName(request.getName());
        arvMedications.setForm(request.getForm());
        arvMedications.setStrength(request.getStrength());
        arvMedications.setManufacturer(request.getManufacturer());
        arvMedications.setDescription(request.getDescription());
        arvMedications.setActive(true);
        arvMedications.setArvRegiment(arvRegimentRepository.findArvRegimentsByNameContaining(request.getArvRegimentName()));
        arvMedicationsRepository.save(arvMedications);
        return true;
    }

    @Override
    public boolean deleteArvMedication(String code) {
        ArvMedications arvMedications = arvMedicationsRepository.getArvMedicationsByCode(code);
        if (arvMedications != null) {
            arvMedicationsRepository.delete(arvMedications);
            return true;
        }
        return false;
    }

    @Override
    public List<ArvMedications> showAllListMedication() {
        return arvMedicationsRepository.findAll();
    }
}