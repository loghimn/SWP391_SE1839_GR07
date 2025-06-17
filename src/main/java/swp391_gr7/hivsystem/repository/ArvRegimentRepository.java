package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.TestResults;

@Repository
public interface ArvRegimentRepository extends JpaRepository<ArvRegiments, Integer> {
    ArvRegiments save(ArvRegiments arvRegiments);

    ArvRegiments findArvRegimentsByArvRegimentID(int arvRegimentID);

    ArvRegiments findArvRegimentsByTestResults(TestResults testResults);
}
