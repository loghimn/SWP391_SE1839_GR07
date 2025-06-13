package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;

import java.util.List;

public interface TestResultService {
    TestResults addTestResult(Long appointmentId, TestResultCreateRequest request);
    List<TestResults> getTestResultsByCustomer(Long customerId);
    String getError();
}
