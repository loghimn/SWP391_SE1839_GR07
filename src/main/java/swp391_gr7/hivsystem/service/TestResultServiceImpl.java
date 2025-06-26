package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
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

    private String error = "";

    @Override
    public TestResults addTestResult(TestResultCreateRequest request) {
        error = "";

        TreatmentPlans treatmentPlan = treatmentPlansRepository.findById(request.getTreatmentPlanId()).orElse(null);
        if (treatmentPlan == null) {
            error = "Treatment plan not found";
            return null;
        }

        Appointments appointment = treatmentPlan.getAppointments();
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        if (appointment == null) {
            error = "Appointment not found";
            return null;
        }

        if (!"Test HIV".equals(appointment.getAppointmentType())) {
            error = "Appointment is not for HIV testing";
            return null;
        }


        TestResults testResult = new TestResults();
        testResult.setAppointments(appointment);
        testResult.setCustomers(appointment.getCustomers());
        testResult.setDoctors(appointment.getDoctors());
        testResult.setTestType("Test HIV");
        testResult.setResultValue(request.isResultValue());
        testResult.setTestDate(appointment.getAppointmentTime());
        testResult.setNotes(request.getNotes());
        testResult.setRe_examination(request.isReExamination());
        testResult.setTreatmentPlan(treatmentPlan);
        testResult.setCD4(request.getCD4());
        testResult.setHivViralLoad(request.getHivViralLoad());

        return testResultRepository.save(testResult);
    }

    @Override
    public List<TestResults> getTestResultsByCustomer(int customerId) {
        return testResultRepository.findByCustomers_CustomerId(customerId);
    }
    @Override
    public TestResults updateTestResult(int id, TestResultCreateRequest request) {
        TestResults existing = testResultRepository.findById(id).orElse(null);
        if (existing == null) {
            error = "Test result not found";
            return null;
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
    public List<TestResults> getMyTestResults(int customerId) {
        return testResultRepository.findByCustomers_CustomerId(customerId);
    }

    @Override
    public String getError() {
        return error;
    }
}
