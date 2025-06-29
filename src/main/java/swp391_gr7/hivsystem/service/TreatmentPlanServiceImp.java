package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.TreatmentPlansCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

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
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Boolean createTreatmentPlan(int doctorId, TreatmentPlansCreateRequest request) {
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));

        Appointments appointment = appointmentRepository.findByAppointmentId(request.getAppointmentId())
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        TreatmentPlans plan = new TreatmentPlans();

        plan.setAppointments(appointment);
        Customers customers = appointment.getCustomers();
        plan.setDoctors(doctor);
        plan.setPlanDescription(request.getTreatmentPlanDescription());
        //System.out.println(arvRegimentRepository.findArvRegimentsByArvRegimentID(request.getArvRegimentId()));
        LocalDate dob = customers.getUsers().getDateOfBirth();
        int age = Period.between(dob, LocalDate.now()).getYears();
        ArvRegiments arvRegiments;
        if (age < 18) {
            // Trẻ em
            if (request.isHistory()) {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 2 - Trẻ em mang thai sau thất bại bậc 1");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 2 - Trẻ em sau thất bại bậc 1");
                }
            } else {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 1 - Trẻ em mang thai");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 1 - Trẻ em tiêu chuẩn");
                }
            }
        } else {
            // Người lớn
            if (request.isHistory()) {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 2 - Người lớn mang thai");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 2 - Người lớn sau thất bại bậc 1");
                }
            } else {
                if (request.isForPregnancy()) {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 1 - Người lớn mang thai");
                } else {
                    arvRegiments = arvRegimentRepository.findArvRegimentsByNameContaining("Bậc 1 - Người lớn tiêu chuẩn");
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

    @Override
    public TreatmentPlans getTreatmentPlanById(int id) {
        return treatmentPlansRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TREATMENT_PLAN_NOT_FOUND));
    }

    @Override
    public TreatmentPlans updateTreatmentPlan(int id, TreatmentPlansCreateRequest request) {
        TreatmentPlans existingPlan = treatmentPlansRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TREATMENT_PLAN_NOT_FOUND));

        existingPlan.setPlanDescription(request.getTreatmentPlanDescription());
        existingPlan.setDosageTime(request.getDosageTime());

        return treatmentPlansRepository.save(existingPlan);
    }

    @Override
    public List<TreatmentPlans> getAll() {
        List<TreatmentPlans> treatmentPlans = treatmentPlansRepository.findAll();
        if (treatmentPlans.isEmpty()) {
            throw new AppException(ErrorCode.TREATMENT_PLAN_NOT_FOUND);
        }
        return treatmentPlans;
    }

    @Override
    public List<TreatmentPlans> getMyTreatmentPlantDoc(int doctorId) {
        List<TreatmentPlans> treatmentPlans = treatmentPlansRepository.findAllByDoctors_DoctorId(doctorId);
        if (treatmentPlans.isEmpty()) {
            throw new AppException(ErrorCode.TREATMENT_PLAN_NOT_FOUND);
        }
        return treatmentPlans;
    }

    @Override
    public List<TreatmentPlans> getMyTreatmentPlantCus(int customerId) {
        List<TreatmentPlans> treatmentPlans = new ArrayList<>();
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

        for (Appointments appointment : customer.getAppointments()) {
            treatmentPlans.addAll(treatmentPlansRepository.findAllByAppointments_AppointmentId(appointment.getAppointmentId()));
        }

        if (treatmentPlans.isEmpty()) {
            throw new AppException(ErrorCode.TREATMENT_PLAN_NOT_FOUND);
        }
        return treatmentPlans;
    }
}
