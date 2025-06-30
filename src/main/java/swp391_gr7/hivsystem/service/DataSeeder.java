package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ArvRegimentRepository regimentRepo;

    @Autowired
    private ArvMedicationsRepository medicationRepo;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private StaffRepository staffRepository;


    void createModel() {
        // Create Users
        Users adminUser = new Users();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("adminpass"));
        adminUser.setEmail("admin@admin.com");
        adminUser.setPhone("0123456789");
        adminUser.setFullName("admin");
        adminUser.setDateOfBirth(LocalDate.of(1990, 1, 1));
        adminUser.setGender("male");
        adminUser.setRole("Admin");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setStatus(true);
        userRepository.save(adminUser);

        Users managerUser = new Users();
        managerUser.setUsername("manager");
        managerUser.setPassword(passwordEncoder.encode("managerpass"));
        managerUser.setEmail("manager1@manager.com");
        managerUser.setPhone("0111111111");
        managerUser.setFullName("Manager One");
        managerUser.setDateOfBirth(LocalDate.of(1985, 5, 10));
        managerUser.setGender("male");
        managerUser.setRole("Manager");
        managerUser.setCreatedAt(LocalDateTime.now());
        managerUser.setStatus(true);
        userRepository.save(managerUser);

        Users doctorUser = new Users();
        doctorUser.setUsername("doctor");
        doctorUser.setPassword(passwordEncoder.encode("doctorpass"));
        doctorUser.setEmail("doctor1@doctor.com");
        doctorUser.setPhone("0222222222");
        doctorUser.setFullName("Doctor One");
        doctorUser.setDateOfBirth(LocalDate.of(1988, 8, 20));
        doctorUser.setGender("female");
        doctorUser.setRole("Doctor");
        doctorUser.setCreatedAt(LocalDateTime.now());
        doctorUser.setStatus(true);
        userRepository.save(doctorUser);

        // Add a staff user
        Users staffUser = new Users();
        staffUser.setUsername("staff");
        staffUser.setPassword(passwordEncoder.encode("staffpass"));
        staffUser.setEmail("staff1@staff.com");
        staffUser.setPhone("0444444444");
        staffUser.setFullName("Staff One");
        staffUser.setDateOfBirth(LocalDate.of(1995, 4, 25));
        staffUser.setGender("female");
        staffUser.setRole("Staff");
        staffUser.setCreatedAt(LocalDateTime.now());
        staffUser.setStatus(true);
        userRepository.save(staffUser);


        // Create Admin
        Admins admin = new Admins();
        admin.setUsers(adminUser);
        admin.setAssignedArea("Area 1");
        adminRepository.save(admin);

        // Create Manager
        Managers manager = new Managers();
        manager.setUsers(managerUser);
        manager.setDepartment("Department 1");
        manager.setOfficePhone("0123456789");
        managerRepository.save(manager);

        // Add a staff
        Staffs staff = new Staffs();
        staff.setUsers(staffUser);
        staff.setDepartment("Department 1");
        staff.setWorkShift(1);
        staff.setAssignedArea("Area 1");
        staff.setManagers(manager);
        staffRepository.save(staff);

        // Create Doctor
        Doctors doctors = new Doctors();
        doctors.setUsers(doctorUser);
        doctors.setDepartment("Department 1");
        doctors.setLicenseNumber("DOC123456");
        doctors.setYearExperience(5);
        doctors.setManagers(manager);
        doctorRepository.save(doctors);


        ArvRegiments r1 = new ArvRegiments();
        r1.setName("Người lớn tiêu chuẩn");
        r1.setLevel(1);
        r1.setDescription("Phác đồ ARV bậc 1 dành cho người lớn không mang thai, điều trị lần đầu");
        r1.setDoctor(doctors);

        ArvRegiments r2 = new ArvRegiments();
        r2.setName("Bậc 1 - Người lớn mang thai");
        r2.setLevel(1);
        r2.setDescription("Phác đồ ARV bậc 1 dành cho phụ nữ mang thai, lần đầu điều trị");
        r2.setDoctor(doctors);

        ArvRegiments r3 = new ArvRegiments();
        r3.setName("Bậc 2 - Người lớn sau thất bại bậc 1");
        r3.setLevel(2);
        r3.setDescription("Phác đồ ARV bậc 2 cho người lớn sau thất bại điều trị bậc 1");
        r3.setDoctor(doctors);

        ArvRegiments r4 = new ArvRegiments();
        r4.setName("Bậc 2 - Người lớn mang thai");
        r4.setLevel(2);
        r4.setDescription("Phác đồ ARV bậc 2 cho phụ nữ mang thai sau thất bại điều trị bậc 1");
        r4.setDoctor(doctors);

        ArvRegiments r5 = new ArvRegiments();
        r5.setName("Bậc 1 - Trẻ em tiêu chuẩn");
        r5.setLevel(1);
        r5.setDescription("Phác đồ ARV bậc 1 cho trẻ em chưa từng điều trị ARV");
        r5.setDoctor(doctors);

        ArvRegiments r6 = new ArvRegiments();
        r6.setName("Bậc 1 - Trẻ em mang thai");
        r6.setLevel(1);
        r6.setDescription("Phác đồ ARV bậc 1 cho trẻ vị thành niên mang thai, chưa điều trị ARV");
        r6.setDoctor(doctors);

        ArvRegiments r7 = new ArvRegiments();
        r7.setName("Bậc 2 - Trẻ em sau thất bại bậc 1");
        r7.setLevel(2);
        r7.setDescription("Phác đồ ARV bậc 2 cho trẻ em sau thất bại phác đồ bậc 1");
        r7.setDoctor(doctors);

        ArvRegiments r8 = new ArvRegiments();
        r8.setName("Bậc 2 - Trẻ em mang thai sau thất bại bậc 1");
        r8.setLevel(2);
        r8.setDescription("Phác đồ ARV bậc 2 cho trẻ vị thành niên mang thai sau thất bại điều trị bậc 1");
        r8.setDoctor(doctors);

        regimentRepo.saveAll(List.of(r1, r2, r3, r4, r5, r6, r7, r8));

        List<ArvMedications> meds = List.of(
                // Người lớn
                new ArvMedications(doctors, "TDF", "Tenofovir disoproxil fumarate", "Viên nén", "300mg", "Mylan", "NRTI người lớn", true, r1),
                new ArvMedications(doctors, "3TC", "Lamivudine", "Viên nén", "150mg", "GSK", "NRTI người lớn", true, r1),
                new ArvMedications(doctors, "EFV", "Efavirenz", "Viên nén", "600mg", "Cipla", "NNRTI người lớn", true, r1),
                new ArvMedications(doctors, "AZT", "Zidovudine", "Siro", "50mg/5ml", "PharmaWomen", "Thai kỳ người lớn", true, r2),
                new ArvMedications(doctors, "NVP", "Nevirapine", "Viên nén", "200mg", "Ranbaxy", "Thai kỳ người lớn", true, r2),
                new ArvMedications(doctors, "3TC", "Lamivudine", "Viên nén", "150mg", "GSK", "NRTI người lớn", true, r2),
                new ArvMedications(doctors, "DTG", "Dolutegravir", "Viên nén", "50mg", "ViiV", "INSTI người lớn", true, r2),
                new ArvMedications(doctors, "ABC", "Abacavir", "Viên nén", "300mg", "MSD", "Bậc 2 người lớn", true, r3),
                new ArvMedications(doctors, "LPV/r", "Lopinavir/ritonavir", "Viên nén", "200/50mg", "AbbVie", "PI người lớn", true, r3),
                new ArvMedications(doctors, "3TC", "Lamivudine", "Viên nén", "150mg", "GSK", "NRTI người lớn", true, r3),
                new ArvMedications(doctors, "ATV/r", "Atazanavir/ritonavir", "Viên nén", "300/100mg", "Pfizer", "PI người lớn", true, r4),
                new ArvMedications(doctors, "3TC", "Lamivudine", "Viên nén", "150mg", "GSK", "NRTI người lớn", true, r4),
                new ArvMedications(doctors, "AZT", "Zidovudine", "Viên nén", "300mg", "PharmaWomen", "NRTI người lớn", true, r4),
                new ArvMedications(doctors, "DTG", "Dolutegravir", "Viên nén", "50mg", "ViiV", "INSTI Người lớn", true, r1),


                // Trẻ em
                new ArvMedications(doctors, "ABC-kid", "Abacavir cho trẻ em", "Viên nén", "60mg", "MSD", "NRTI trẻ em", true, r5),
                new ArvMedications(doctors, "3TC-kid", "Lamivudine cho trẻ em", "Viên nén", "30mg", "GSK", "NRTI trẻ em", true, r5),
                new ArvMedications(doctors, "EFV-kid", "Efavirenz trẻ em", "Viên nén", "200mg", "Cipla", "NNRTI trẻ em", true, r5),
                new ArvMedications(doctors, "LPV/r-kid", "Lopinavir/ritonavir cho trẻ em", "Viên nén", "40/10mg", "AbbVie", "PI trẻ em", true, r7),
                new ArvMedications(doctors, "ABC-kid", "Abacavir cho trẻ em", "Viên nén", "60mg", "MSD", "NRTI trẻ em", true, r7),
                new ArvMedications(doctors, "3TC-kid", "Lamivudine cho trẻ em", "Viên nén", "30mg", "GSK", "NRTI trẻ em", true, r7),
                new ArvMedications(doctors, "AZT-kid", "Zidovudine dạng siro trẻ em", "Siro", "10mg/5ml", "PharmaKids", "Thai kỳ trẻ em", true, r6),
                new ArvMedications(doctors, "3TC-kid", "Lamivudine trẻ em", "Viên nén", "30mg", "GSK", "NRTI trẻ em", true, r6),
                new ArvMedications(doctors, "LPV/r-kid", "Lopinavir/ritonavir trẻ em", "Viên nén", "40/10mg", "AbbVie", "PI trẻ em", true, r6),
                new ArvMedications(doctors, "ABC-kid", "Abacavir cho trẻ em", "Viên nén", "60mg", "MSD", "NRTI trẻ em", true, r8),
                new ArvMedications(doctors, "3TC-kid", "Lamivudine cho trẻ em", "Viên nén", "30mg", "GSK", "NRTI trẻ em", true, r8),
                new ArvMedications(doctors, "LPV/r-kid", "Lopinavir/ritonavir cho trẻ em", "Viên nén", "40/10mg", "AbbVie", "PI trẻ em", true, r8),
                new ArvMedications(doctors, "AZT-kid", "Zidovudine dạng siro trẻ em", "Siro", "10mg/5ml", "PharmaKids", "Dự phòng thay thế", true, r8)
        );

        medicationRepo.saveAll(meds);


        // Create Blogs
        Blogs blog1 = new Blogs();
        blog1.setDoctors(doctors);
        blog1.setTitle("Thế giới có thể kết thúc đại dịch AIDS vào năm 2030");
        blog1.setContent("Theo UNAIDS, với cam kết chính trị mạnh mẽ và đầu tư tài chính đủ, "
                + "đích đến chấm dứt AIDS vào năm 2030 là khả thi, đặc biệt ở các khu vực Đông – Nam châu Phi.");
        blog1.setImageUrl("https://i.imgur.com/zlgjYkN.jpg"); // minh họa xét nghiệm HIV
        blog1.setSource("VAAC (dịch từ UNAIDS)");
        blog1.setCreateAt(LocalDate.now());
        blogRepository.save(blog1);


        Blogs blog2 = new Blogs();
        blog2.setDoctors(doctors);
        blog2.setTitle("Hội nghị tổng kết hoạt động phòng, chống HIV/AIDS năm 2024");
        blog2.setContent("VAAC đã tổ chức hội nghị tại Đà Nẵng tổng kết năm 2024, đánh giá thành tựu "
                + "và xây dựng kế hoạch tới năm 2025, với nhiều văn bản như Nghị định 141/2024/NĐ-CP và Thông tư 26/2024.");
        blog2.setImageUrl("https://i.imgur.com/6KukPxL.jpg"); // minh họa hội nghị y tế
        blog2.setSource("VAAC");
        blog2.setCreateAt(LocalDate.now());
        blogRepository.save(blog2);


        Blogs blog3 = new Blogs();
        blog3.setDoctors(doctors);
        blog3.setTitle("Hành trình 21 năm chăm sóc, điều trị HIV/AIDS tại Việt Nam");
        blog3.setContent("Dự án HAIVN/BIDMC đã hỗ trợ kỹ thuật từ 2003, đào tạo hơn 10.000 nhân viên y tế "
                + "và mở rộng điều trị HIV từ 1.000 lên 170.000 bệnh nhân vào năm 2023.");
        blog3.setImageUrl("https://i.imgur.com/sRA3aZq.jpg"); // minh họa hỗ trợ kỹ thuật y tế
        blog3.setSource("VAAC");
        blog3.setCreateAt(LocalDate.now());
        blogRepository.save(blog3);


        Blogs blog4 = new Blogs();
        blog4.setDoctors(doctors);
        blog4.setTitle("K=K: Không phát hiện = Không lây truyền");
        blog4.setContent("Chiến dịch K=K khẳng định: người có tải lượng HIV <200 bản sao/ml "
                + "và uống ARV đều đặn không thể lây truyền HIV qua đường tình dục.");
        blog4.setImageUrl("https://i.imgur.com/Bxfu4qG.jpg"); // minh họa thông điệp y tế
        blog4.setSource("VAAC");
        blog4.setCreateAt(LocalDate.now());
        blogRepository.save(blog4);


        Blogs blog5 = new Blogs();
        blog5.setDoctors(doctors);
        blog5.setTitle("Vai trò của người sống chung với HIV");
        blog5.setContent("Người nhiễm HIV có thể đóng góp hiệu quả vào công tác phòng chống HIV/AIDS, "
                + "giúp giảm kỳ thị và hỗ trợ cộng đồng tiếp cận điều trị.");
        blog5.setImageUrl("https://i.imgur.com/E91d0oE.jpg"); // minh họa cộng đồng hỗ trợ
        blog5.setSource("VAAC");
        blog5.setCreateAt(LocalDate.now());
        blogRepository.save(blog5);


        Materials material1 = new Materials();
        material1.setDoctor(doctors);
        material1.setTitle("Kiến thức cơ bản về HIV/AIDS");
        material1.setContent("Phân biệt HIV và AIDS, đường lây truyền, triệu chứng, và cách phòng tránh chủ yếu.");
        material1.setImageUrl("https://i.imgur.com/zlgjYkN.jpg");
        material1.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material1.setCreateAt(LocalDate.now());
        materialRepository.save(material1);

        Materials material2 = new Materials();
        material2.setDoctor(doctors);
        material2.setTitle("Đào tạo kỹ thuật xét nghiệm HIV");
        material2.setContent("Hướng dẫn kỹ thuật thực hiện xét nghiệm; đảm bảo an toàn sinh học và chất lượng phòng xét nghiệm.");
        material2.setImageUrl("https://i.imgur.com/sRA3aZq.jpg");
        material2.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material2.setCreateAt(LocalDate.now());
        materialRepository.save(material2);

        Materials material3 = new Materials();
        material3.setDoctor(doctors);
        material3.setTitle("HIV kháng thuốc: Sổ tay chuyên môn");
        material3.setContent("Hướng dẫn quản lý và điều trị người nhiễm HIV có kháng thuốc, theo dõi, và can thiệp y tế kịp thời.");
        material3.setImageUrl("https://i.imgur.com/Bxfu4qG.jpg");
        material3.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material3.setCreateAt(LocalDate.now());
        materialRepository.save(material3);

        Materials material4 = new Materials();
        material4.setDoctor(doctors);
        material4.setTitle("Hướng dẫn triển khai BHYT cho người nhiễm HIV");
        material4.setContent("Giúp đảm bảo tài chính cho người nhiễm HIV trong tiếp cận thuốc ARV, xét nghiệm và điều trị lâu dài.");
        material4.setImageUrl("https://i.imgur.com/6KukPxL.jpg");
        material4.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material4.setCreateAt(LocalDate.now());
        materialRepository.save(material4);

        Materials material5 = new Materials();
        material5.setDoctor(doctors);
        material5.setTitle("Thuốc kháng HIV (ARV)");
        material5.setContent("Giới thiệu các loại thuốc ARV, cơ chế hoạt động, lợi ích và hướng dẫn sử dụng cơ bản.");
        material5.setImageUrl("https://i.imgur.com/E91d0oE.jpg");
        material5.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material5.setCreateAt(LocalDate.now());
        materialRepository.save(material5);


    }

    @Override
    public void run(String... args) throws Exception {
        List<ArvMedications> meds = medicationRepo.findAll();
        List<ArvRegiments> regiments = regimentRepo.findAll();
        if (meds.size() == 0 && regiments.size() == 0) {
            createModel();
        }
    }
}