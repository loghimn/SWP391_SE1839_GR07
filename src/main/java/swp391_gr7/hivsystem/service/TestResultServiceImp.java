package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;
import swp391_gr7.hivsystem.repository.*;

@Service
public class TestResultServiceImp implements TestResultService {
    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TreatmentPlansRepository treatmentPlansRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Boolean createTestResult(TestResultCreateRequest request) {
        TestResults testResults = new TestResults();
        testResults.setCustomers(customerRepository.getCustomersByCustomerId((request.getCustomerId())));
        testResults.setDoctors(doctorRepository.getDoctorsByDoctorId(request.getDoctorId()));
        testResults.setTestType(request.getTypeTest());
        testResults.setResultValue(request.getResultValue());
        testResults.setTestDate(request.getTestDate());
        testResults.setNotes(request.getNotes());
        testResults.setTreatmentPlan(treatmentPlansRepository.getTreatmentPlansByTreatmentPlanID(request.getTreatmentPlanId()));
        testResults.setAppointments(appointmentRepository.getAppointmentsByAppointmentId(request.getAppointmentId()));
        testResults.setRe_examination(request.isReExamination());
        testResults = testResultRepository.save(testResults);
        if (testResults != null) {
            return true;
        } else return false;
    }
}
