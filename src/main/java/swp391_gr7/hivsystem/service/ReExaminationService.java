package swp391_gr7.hivsystem.service;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391_gr7.hivsystem.model.TestResults;

public interface ReExaminationService {
    void handleReExamination(TestResults result);
}
