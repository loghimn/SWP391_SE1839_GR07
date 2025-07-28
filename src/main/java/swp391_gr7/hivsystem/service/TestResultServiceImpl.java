package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.model.TestResults;
import swp391_gr7.hivsystem.model.TreatmentPlans;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.TestResultRepository;
import swp391_gr7.hivsystem.repository.TreatmentPlansRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private TreatmentPlansRepository treatmentPlansRepository;


    @Override
    public TestResults addTestResult(TestResultCreateRequest request) {


        TreatmentPlans treatmentPlan = treatmentPlansRepository.findById(request.getTreatmentPlanId()).orElse(null);
        if (treatmentPlan == null) {
            throw new AppException(ErrorCode.TREATMENT_PLAN_NOT_FOUND);
        }
        if(!treatmentPlan.isStatus()){
            throw new AppException(ErrorCode.TREATMENT_PLAN_IS_NOT_ACTIVE);
        }

        Appointments appointment = treatmentPlan.getAppointments();
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        if (appointment == null) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }

        if (!"Test HIV".equals(appointment.getAppointmentType()) &&
                !"Re-Examination".equals(appointment.getAppointmentType())) {
            throw new AppException(ErrorCode.APPOINTMENT_TYPE_IS_NOT_HIV_TEST);
        }


        TestResults testResult = new TestResults();
        testResult.setAppointments(appointment);
        testResult.setCustomers(appointment.getCustomers());
        testResult.setDoctors(appointment.getDoctors());
        testResult.setTestType("Test HIV");
        testResult.setResultValue(request.isResultValue());
        testResult.setTestDate(appointment.getStartTime().toLocalDate());
        testResult.setNotes(request.getNotes());
        testResult.setRe_examination(request.isReExamination());
        testResult.setTreatmentPlan(treatmentPlan);
        testResult.setCD4(request.getCD4());
        testResult.setHivViralLoad(request.getHivViralLoad());

        treatmentPlan.setStatus(false);
        treatmentPlansRepository.save(treatmentPlan);

        return testResultRepository.save(testResult);
    }

    @Override
    public List<TestResults> getTestResultsByCustomer(int customerId) {
        if (testResultRepository.findByCustomers_CustomerId(customerId) == null) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }
        return testResultRepository.findByCustomers_CustomerId(customerId);
    }

    @Override
    public TestResults updateTestResult(int id, TestResultCreateRequest request) {
        TestResults existing = testResultRepository.findById(id).orElse(null);
        if (existing == null) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }

        existing.setResultValue(request.isResultValue());
        existing.setNotes(request.getNotes());
        existing.setRe_examination(request.isReExamination());
        existing.setCD4(request.getCD4());
        existing.setHivViralLoad(request.getHivViralLoad());

        // Nếu bạn cho phép update thời gian test, có thể thêm dòng sau:
        // existing.setTestDate(request.getTestDate());

        return testResultRepository.save(existing);
    }

    @Override
    public TestResults getTestResultById(int id) {
        return testResultRepository.findById(id).orElse(null);
    }

    @Override
    public List<TestResults> getMyTestResultsCus(int customerId) {
        if (testResultRepository.findByCustomers_CustomerId(customerId) == null) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }
        return testResultRepository.findByCustomers_CustomerId(customerId);
    }

    @Override
    public List<TestResults> getMyTestResultsDoc(int doctorId) {
        if (testResultRepository.findByDoctors_DoctorId(doctorId) == null) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }
        return testResultRepository.findByDoctors_DoctorId(doctorId);
    }

    @Override
    public List<TestResults> getAllTestResults() {
        List<TestResults> results = testResultRepository.findAll();
        if (results.isEmpty()) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }
        return results;
    }

    @Override
    public List<TestResults> getAllTestResultsNoHaveRemin() {
        List<TestResults> results = testResultRepository.findTestResultsNoHaveReminder();
        if (results.isEmpty()) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }
        return results;
    }

}
