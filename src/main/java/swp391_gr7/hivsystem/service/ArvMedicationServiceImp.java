package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ArvMedicationCreateRequest;
import swp391_gr7.hivsystem.dto.response.ArvMedicationResponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.repository.ArvMedicationsRepository;
import swp391_gr7.hivsystem.repository.ArvRegimentRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        ArvRegiments existingArvRegiment = arvRegimentRepository.findArvRegimentsByNameContaining(request.getArvRegimentName());
        if (existingArvRegiment == null) {
            throw new AppException(ErrorCode.ARV_REGIMENT_NOT_FOUND);
        }

        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));


        ArvMedications arvMedications = new ArvMedications();
        arvMedications.setCode(request.getCode());
        arvMedications.setName(request.getName());
        arvMedications.setForm(request.getForm());
        arvMedications.setStrength(request.getStrength());
        arvMedications.setManufacturer(request.getManufacturer());
        arvMedications.setDescription(request.getDescription());
        arvMedications.setActive(true);
        arvMedications.setArvRegiment(existingArvRegiment);

        arvMedications.setDoctor(doctor);
        arvMedicationsRepository.save(arvMedications);
        return true;
    }

    @Override
    public boolean updateArvMedication(ArvMedicationCreateRequest request, int id) {
        ArvMedications arvMedications = arvMedicationsRepository.findById(id).orElseThrow(() -> new  AppException(ErrorCode.ARV_MEDICATION_NOT_FOUND));
        if (arvMedications == null) {
            throw new AppException(ErrorCode.ARV_MEDICATION_NOT_FOUND);
        }
        ArvRegiments existingArvRegiment = arvRegimentRepository.findArvRegimentsByNameContaining(request.getArvRegimentName());
        if (existingArvRegiment == null) {
            throw new AppException(ErrorCode.ARV_REGIMENT_NOT_FOUND);
        }

        arvMedications.setName(request.getName());
        arvMedications.setForm(request.getForm());
        arvMedications.setStrength(request.getStrength());
        arvMedications.setManufacturer(request.getManufacturer());
        arvMedications.setDescription(request.getDescription());
        arvMedications.setActive(true);
        arvMedications.setArvRegiment(existingArvRegiment);
        arvMedicationsRepository.save(arvMedications);
        return true;
    }

    @Override
    public boolean deleteArvMedication(int code) {
        ArvMedications arvMedications = arvMedicationsRepository.findById(code).orElseThrow(() ->  new AppException(ErrorCode.ARV_MEDICATION_NOT_FOUND));
        if (arvMedications != null) {
            arvMedicationsRepository.delete(arvMedications);
            return true;
        } else if (arvMedications == null) {
            throw new AppException(ErrorCode.ARV_MEDICATION_NOT_FOUND);
        }
        return false;
    }

//    @Override
//    public List<ArvMedications> showAllListMedication() {
//        List<ArvMedications> arvMedications = arvMedicationsRepository.findAll();
//        if (arvMedications.isEmpty()) {
//            throw new AppException(ErrorCode.ARV_MEDICATION_NOT_FOUND);
//        }
//        return arvMedications;
//    }

    @Override
    public List<ArvMedications> getMedicationByArvRegimentId(int arvRegimentId) {
        ArvRegiments arvRegiment = arvRegimentRepository.findById(arvRegimentId)
                .orElseThrow(() -> new AppException(ErrorCode.ARV_REGIMENT_NOT_FOUND));

        List<ArvMedications> arvMedications = arvMedicationsRepository.findByArvRegiment(arvRegiment);
        if (arvMedications.isEmpty()) {
            throw new AppException(ErrorCode.ARV_MEDICATION_NOT_FOUND);
        }
        return arvMedications;
    }

    @Override
    public List<ArvMedicationResponse> showAllListMedication() {
        List<ArvMedications> meds = arvMedicationsRepository.findAll();
        for( ArvMedications m : meds) {
            System.out.println("Medication: " + m.getName() + ", ARV Regiment: " + (m.getArvRegiment() != null ? m.getArvRegiment().getName() : "None"));
        }
        return meds.stream()
                .map(ArvMedicationResponse::new)
                .collect(Collectors.toList());
    }
}