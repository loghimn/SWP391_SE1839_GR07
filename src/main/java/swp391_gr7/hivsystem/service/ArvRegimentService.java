package swp391_gr7.hivsystem.service;

    import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
    import swp391_gr7.hivsystem.model.ArvMedications;
    import swp391_gr7.hivsystem.model.ArvRegiments;

    import java.util.List;
    import java.util.Optional;

    public interface ArvRegimentService {
        Boolean createArvRegiment(ArvRegimentCreateRequest request, int doctorId);

        List<ArvRegiments> getAllArvRegiments();

        Optional<ArvRegiments> getArvRegimentById(int id);

        Boolean updateArvRegiment(int id, ArvRegimentCreateRequest request);

        List<ArvMedications> suggestArvMedication(int treatmentPlanId);

        List<ArvRegiments> getArvRegimentsByDoctorId(int doctorId);

        List<ArvRegiments> getMyArvRegiments(int doctorId);


    }