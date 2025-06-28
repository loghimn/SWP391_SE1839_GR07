package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.TestResults;

import java.util.List;

@Repository
public interface ArvRegimentRepository extends JpaRepository<ArvRegiments, Integer> {
    ArvRegiments save(ArvRegiments arvRegiments);

    ArvRegiments findArvRegimentsByArvRegimentID(int arvRegimentID);

//    ArvRegiments findArvRegimentsByDescriptionContaining(String description);

    //ArvRegiments findArvRegimentsByTestResults(TestResults testResults);

    List<ArvRegiments> findArvRegimentsByDoctor_DoctorId(int doctorDoctorId);

    ArvRegiments findArvRegimentsByNameContaining(String name);
}
