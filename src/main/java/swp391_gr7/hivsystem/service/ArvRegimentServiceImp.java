package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ArvRegimentCreateRequest;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.TestResults;
import swp391_gr7.hivsystem.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class ArvRegimentServiceImp implements ArvRegimentService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ArvRegimentRepository arvRegimentRepository;
    @Autowired
    TestResultRepository testResultRepository;
    @Autowired
    ArvMedicationsRepository arvMedicationRepository;

    @Override
    public Boolean createArvRegiment(ArvRegimentCreateRequest request) {
        ArvRegiments arvRegiments = new ArvRegiments();
        arvRegiments.setDescription(request.getDescription());
        arvRegiments.setLevel(Integer.valueOf(request.getLevel()));
        arvRegiments.setForPregnancy(request.isForPregnancy());
        arvRegiments = arvRegimentRepository.save(arvRegiments);
        return arvRegiments != null;
    }

    @Override
    public List<ArvMedications> suggestArvMedication(int testResultId) {
        TestResults testResults = testResultRepository.findTestResultsByTestResultID(testResultId);
        if (testResults == null) {
            return new ArrayList<>();
        }
        Customers customers = testResults.getCustomers();
        if (customers == null || customers.getUsers() == null || customers.getUsers().getDateOfBirth() == null) {
            return new ArrayList<>();
        }
        ArvRegiments arvRegiments = arvRegimentRepository.findArvRegimentsByTestResults(testResults);
        if (arvRegiments == null) {
            return new ArrayList<>();
        }

        LocalDate dob = customers.getUsers().getDateOfBirth();
        int age = Period.between(dob, LocalDate.now()).getYears();

        // Dùng List tạm thời
        List<ArvMedications> suggestions = new ArrayList<>();

        if (age < 15) {
            suggestions.addAll(arvMedicationRepository.findAllByDescriptionContainingIgnoreCase("trẻ em"));
        } else {
            suggestions.addAll(arvMedicationRepository.findAllByDescriptionContainingIgnoreCase("người lớn"));
        }

        String levelKeyword = "bậc " + arvRegiments.getLevel();
        suggestions.addAll(arvMedicationRepository.findAllByDescriptionContainingIgnoreCase(levelKeyword));

        if (arvRegiments.isForPregnancy()) {
            suggestions.addAll(arvMedicationRepository.findAllByDescriptionContainingIgnoreCase("phụ nữ mang thai"));
        }

        // Lọc isActive và loại trùng bằng cách dùng LinkedHashSet rồi chuyển lại List
        List<ArvMedications> filtered = new ArrayList<>(
                new LinkedHashSet<>(suggestions.stream()
                        .filter(ArvMedications::isActive)
                        .toList())
        );

        return filtered;
    }





}
