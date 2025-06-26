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
        managerUser.setUsername("manager1");
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
        doctorUser.setUsername("doctor1");
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
                new ArvMedications(doctors, "ABC", "Abacavir", "Viên nén", "300mg", "MSD", "Bậc 2 người lớn", true, r3),
                new ArvMedications(doctors, "LPV/r", "Lopinavir/ritonavir", "Viên nén", "200/50mg", "AbbVie", "PI người lớn", true, r3),
                new ArvMedications(doctors, "ATV/r", "Atazanavir/ritonavir", "Viên nén", "300/100mg", "Pfizer", "PI người lớn", true, r4),

                // Trẻ em
                new ArvMedications(doctors, "ABC-kid", "Abacavir cho trẻ em", "Viên nén", "60mg", "MSD", "NRTI trẻ em", true, r5),
                new ArvMedications(doctors, "3TC-kid", "Lamivudine cho trẻ em", "Viên nén", "30mg", "GSK", "NRTI trẻ em", true, r5),
                new ArvMedications(doctors, "LPV/r-kid", "Lopinavir/ritonavir cho trẻ em", "Viên nén", "40/10mg", "AbbVie", "PI trẻ em", true, r7),
                new ArvMedications(doctors, "AZT-kid", "Zidovudine dạng siro trẻ em", "Siro", "10mg/5ml", "PharmaKids", "Thai kỳ trẻ em", true, r6)
        );

        medicationRepo.saveAll(meds);
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