package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import swp391_gr7.hivsystem.model.ArvMedications;
import swp391_gr7.hivsystem.model.ArvRegiments;
import swp391_gr7.hivsystem.repository.ArvMedicationsRepository;
import swp391_gr7.hivsystem.repository.ArvRegimentRepository;
import swp391_gr7.hivsystem.repository.TreatmentPlansRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ArvRegimentRepository regimentRepo;

    @Autowired
    private ArvMedicationsRepository medicationRepo;


    void createModel() {
        // Phác đồ người lớn
        ArvRegiments r1 = new ArvRegiments(1, "Bậc 1 - Người lớn tiêu chuẩn");
        ArvRegiments r2 = new ArvRegiments(1, "Bậc 1 - Người lớn mang thai");
        ArvRegiments r3 = new ArvRegiments(2, "Bậc 2 - Người lớn sau thất bại bậc 1");
        ArvRegiments r4 = new ArvRegiments(2, "Bậc 2 - Người lớn mang thai");

        // Thêm phác đồ cho trẻ em
        ArvRegiments r5 = new ArvRegiments(1, "Bậc 1 - Trẻ em tiêu chuẩn");
        ArvRegiments r6 = new ArvRegiments(1, "Bậc 1 - Trẻ em mang thai");
        ArvRegiments r7 = new ArvRegiments(2, "Bậc 2 - Trẻ em sau thất bại bậc 1");
        ArvRegiments r8 = new ArvRegiments(2, "Bậc 2 - Trẻ em mang thai sau thất bại bậc 1");

        regimentRepo.saveAll(List.of(r1, r2, r3, r4, r5, r6, r7, r8));

        List<ArvMedications> meds = List.of(
                // Người lớn
                new ArvMedications("TDF", "Tenofovir disoproxil fumarate", "Viên nén", "300mg", "Mylan", "NRTI người lớn", true, r1),
                new ArvMedications("3TC", "Lamivudine", "Viên nén", "150mg", "GSK", "NRTI người lớn", true, r1),
                new ArvMedications("EFV", "Efavirenz", "Viên nén", "600mg", "Cipla", "NNRTI người lớn", true, r1),
                new ArvMedications("AZT", "Zidovudine", "Siro", "50mg/5ml", "PharmaWomen", "Thai kỳ người lớn", true, r2),
                new ArvMedications("NVP", "Nevirapine", "Viên nén", "200mg", "Ranbaxy", "Thai kỳ người lớn", true, r2),
                new ArvMedications("ABC", "Abacavir", "Viên nén", "300mg", "MSD", "Bậc 2 người lớn", true, r3),
                new ArvMedications("LPV/r", "Lopinavir/ritonavir", "Viên nén", "200/50mg", "AbbVie", "PI người lớn", true, r3),
                new ArvMedications("ATV/r", "Atazanavir/ritonavir", "Viên nén", "300/100mg", "Pfizer", "PI người lớn", true, r4),

                // Trẻ em
                new ArvMedications("ABC-kid", "Abacavir cho trẻ em", "Viên nén", "60mg", "MSD", "NRTI trẻ em", true, r5),
                new ArvMedications("3TC-kid", "Lamivudine cho trẻ em", "Viên nén", "30mg", "GSK", "NRTI trẻ em", true, r5),
                new ArvMedications("LPV/r-kid", "Lopinavir/ritonavir cho trẻ em", "Viên nén", "40/10mg", "AbbVie", "PI trẻ em", true, r7),
                new ArvMedications("AZT-kid", "Zidovudine dạng siro trẻ em", "Siro", "10mg/5ml", "PharmaKids", "Thai kỳ trẻ em", true, r6)
                // bạn có thể thêm thuốc khác theo nhu cầu
        );

        medicationRepo.saveAll(meds);
    }

    @Override
    public void run(String... args) throws Exception {
         List<ArvMedications> meds = medicationRepo.findAll();
         List<ArvRegiments> regiments = regimentRepo.findAll();
       if(meds.size() == 0 && regiments.size() == 0){
           createModel();
       }
    }
}