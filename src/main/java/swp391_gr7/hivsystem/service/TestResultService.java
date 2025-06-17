package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;

public interface TestResultService {
    Boolean createTestResult(TestResultCreateRequest request);
}
