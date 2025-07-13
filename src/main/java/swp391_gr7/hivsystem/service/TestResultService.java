package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
import swp391_gr7.hivsystem.model.TestResults;

import java.util.List;

public interface TestResultService {
    TestResults addTestResult(TestResultCreateRequest request);

    TestResults updateTestResult(int id, TestResultCreateRequest request);

    TestResults getTestResultById(int id);

    List<TestResults> getTestResultsByCustomer(int customerId);

    List<TestResults> getMyTestResultsCus(int customerId);

    List<TestResults> getMyTestResultsDoc(int doctorId);

    List<TestResults> getAllTestResults();

    List<TestResults> getAllTestResultsNoHaveRemin();
}
