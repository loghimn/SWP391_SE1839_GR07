package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;

import java.util.List;

public interface TestResultService {
    TestResults addTestResult(TestResultCreateRequest request);

    TestResults updateTestResult(int id, TestResultCreateRequest request);

    TestResults getTestResultById(int id);

    List<TestResults> getTestResultsByCustomer(int customerId);

    String getError();

    List<TestResults> getMyTestResults(int customerId);
}
