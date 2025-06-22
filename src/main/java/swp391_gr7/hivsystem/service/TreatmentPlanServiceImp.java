package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.TreatmentPlans;
import swp391_gr7.hivsystem.repository.ArvRegimentRepository;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.TreatmentPlansRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentPlanServiceImp implements TreatmentPlanService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ArvRegimentRepository arvRegimentRepository;
    @Autowired
    TreatmentPlansRepository treatmentPlansRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Boolean createTreatmentPlan(TreatmentPlansCreateRequest request) {
        TreatmentPlans plan = new TreatmentPlans();
        Customers customers = customerRepository.getCustomersByCustomerId(request.getCustomerId());
        plan.setDoctors(doctorRepository.getDoctorsByDoctorId(request.getDoctorId()));
        plan.setPlanDescription(request.getTreatmentPlanDescription());
        //System.out.println(arvRegimentRepository.findArvRegimentsByArvRegimentID(request.getArvRegimentId()));
        LocalDate dob = customers.getUsers().getDateOfBirth();
        int age = Period.between(dob, LocalDate.now()).getYears();
        ArvRegiments arvRegiments;
        if (age < 18) {
            // Trẻ em
            if (request.isHistory()) {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 2 - Trẻ em mang thai sau thất bại bậc 1");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 2 - Trẻ em sau thất bại bậc 1");
                }
            } else {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 1 - Trẻ em mang thai");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 1 - Trẻ em tiêu chuẩn");
                }
            }
        } else {
            // Người lớn
            if (request.isHistory()) {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 2 - Người lớn mang thai");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 2 - Người lớn sau thất bại bậc 1");
                }
            } else {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 1 - Người lớn mang thai");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByDescriptionContaining("Bậc 1 - Người lớn tiêu chuẩn");
                }
            }
        }

        plan.setArvReqimentID(arvRegiments);

        plan.setDosageTime(request.getDosageTime());

        plan = treatmentPlansRepository.save(plan);
        if (plan != null) {
            return true;
        }
        return false;
    }
}
