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
    public TestResults addTestResult(int appointmentId, int treatmentplanId, TestResultCreateRequest request) {
        error = "";

        Appointments appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment == null) {
            error = "Appointment not found";
            return null;
        }

        if (!"Test HIV".equals(appointment.getAppointmentType())) {
            error = "Appointment is not for HIV testing";
            return null;
        }
        TreatmentPlans treatmentPlan = treatmentPlansRepository.findById(treatmentplanId).orElse(null);
        if (treatmentPlan == null) {
            error = "Treatment plan not found";
            return null;
        }

        TestResults testResult = new TestResults();
        testResult.setAppointments(appointment);
        testResult.setCustomers(appointment.getCustomers());
        testResult.setDoctors(appointment.getDoctors());
        testResult.setTestType("HIV Test");
        testResult.setResultValue(request.isResultValue());
        testResult.setTestDate(appointment.getAppointmentTime());
        testResult.setNotes(request.getNotes());
        testResult.setRe_examination(request.isReExamination());
        testResult.setTreatmentPlan(treatmentPlan);

        return testResultRepository.save(testResult);
    }

    @Override
    public List<TestResults> getTestResultsByCustomer(int customerId) {
        return testResultRepository.findByCustomers_CustomerId(customerId);
    }

    @Override
    public String getError() {
        return error;
    }
}
