package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SchedulesRepository schedulesRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private TreatmentPlansRepository treatmentPlansRepository;
    @Autowired
    private ConsultationRepository consultationRepository;


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
        staffUser.setEmail("staff1@fpt.edu.vn");
        staffUser.setPhone("0444444444");
        staffUser.setFullName("Staff One");
        staffUser.setDateOfBirth(LocalDate.of(1995, 4, 25));
        staffUser.setGender("female");
        staffUser.setRole("Staff");
        staffUser.setCreatedAt(LocalDateTime.now());
        staffUser.setStatus(true);
        userRepository.save(staffUser);

        Users staffUser2 = new Users();
        staffUser2.setUsername("staff2");
        staffUser2.setPassword(passwordEncoder.encode("staff2pass"));
        staffUser2.setEmail("staff2@fpt.edu.vn");
        staffUser2.setPhone("0444444445");
        staffUser2.setFullName("Staff Two");
        staffUser2.setDateOfBirth(LocalDate.of(1995, 4, 25));
        staffUser2.setGender("female");
        staffUser2.setRole("Staff");
        staffUser2.setCreatedAt(LocalDateTime.now());
        staffUser2.setStatus(true);
        userRepository.save(staffUser2);

        // Add a customer user
        Users customerUser = new Users();
        customerUser.setUsername("customer");
        customerUser.setPassword(passwordEncoder.encode("customerpass"));
        customerUser.setEmail("customer1@gmail.com");
        customerUser.setPhone("0555555555");
        customerUser.setFullName("Customer One");
        customerUser.setDateOfBirth(LocalDate.of(1995, 4, 25));
        customerUser.setGender("female");
        customerUser.setRole("Customer");
        customerUser.setCreatedAt(LocalDateTime.now());
        customerUser.setStatus(true);
        userRepository.save(customerUser);


        // Create Admin
        Admins admin = new Admins();
        admin.setUsers(adminUser);
        admin.setAssignedArea("Area 1");
        adminRepository.save(admin);

        // Create Manager
        Managers manager = new Managers();
        manager.setUsers(managerUser);
//        manager.setDepartment("Department 1");
        manager.setOfficePhone("0123456789");
        managerRepository.save(manager);

        // Add a staff
        Staffs staff = new Staffs();
        staff.setUsers(staffUser);
//        staff.setDepartment("Department 1");
        staff.setWorkShift(1);
//        staff.setAssignedArea("Area 1");
        staff.setManagers(manager);
        staffRepository.save(staff);

        Staffs staff2 = new Staffs();
        staff2.setUsers(staffUser2);
//        staff2.setDepartment("Department 2");
        staff2.setWorkShift(2);
//        staff2.setAssignedArea("Area 2");
        staff2.setManagers(manager);
        staffRepository.save(staff2);

        //Create Customer
        Customers customers = new Customers();
        customers.setUsers(customerUser);
        customers.setAddress("Bien Hoa");
        customers.setManagers(manager);
        customerRepository.save(customers);

        // Create Doctor
        Doctors doctors = new Doctors();
        doctors.setUsers(doctorUser);
//        doctors.setDepartment("Department 1");
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

        //Create ScheduleAdd commentMore actions
        Schedules schedules = null;
        for (int i = 0; i < 25; i++) {
            schedules = new Schedules();
            schedules.setManagers(manager);
            schedules.setDoctors(doctors);
            schedules.setNotes("Ngày đi làm");
            schedules.setWorkDate(LocalDate.of(2025, 8, 1 + i));
            schedulesRepository.save(schedules);
        }

        //Create Medical RecordAdd commentMore actions
        MedicalRecords medicalRecords = new MedicalRecords();
        medicalRecords.setCustomers(customers);
        medicalRecords.setRecordDate(LocalDate.now());
        medicalRecords.setDiagnosis("HIV/AIDS");
        medicalRecords.setTreatment("NOT");
        medicalRecordRepository.save(medicalRecords);

        //Create AppointmentAdd commentMore actions
        LocalTime fixedStartTime = LocalTime.of(8, 0);
        Duration duration = Duration.ofHours(2);
        Appointments appointments1 = new Appointments();
        appointments1.setDoctors(doctors);
        appointments1.setMedicalRecords(medicalRecords);
        appointments1.setCustomers(customers);
        appointments1.setStatus(false);
        LocalDateTime start1 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(8, 0));
        appointments1.setStartTime(start1);
        appointments1.setEndTime(start1.plusHours(2));
        appointments1.setAnonymous(true);
        appointments1.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointments1.setStaffs(staff);
        appointments1.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments1);

        Appointments appointments1_1 = new Appointments();
        appointments1_1.setDoctors(doctors);
        appointments1_1.setMedicalRecords(medicalRecords);
        appointments1_1.setCustomers(customers);
        appointments1_1.setStatus(false);
        LocalDateTime start1_1 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(10, 0));
        appointments1_1.setStartTime(start1_1);
        appointments1_1.setEndTime(start1_1.plusHours(2));
        appointments1_1.setAnonymous(true);
        appointments1_1.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointments1_1.setStaffs(staff);
        appointments1_1.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments1_1);

        Appointments appointments1_2 = new Appointments();
        appointments1_2.setDoctors(doctors);
        appointments1_2.setMedicalRecords(medicalRecords);
        appointments1_2.setCustomers(customers);
        appointments1_2.setStatus(false);
        LocalDateTime start1_2 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(14, 0));
        appointments1_2.setStartTime(start1_2);
        appointments1_2.setEndTime(start1_2.plusHours(2));
        appointments1_2.setAnonymous(true);
        appointments1_2.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointments1_2.setStaffs(staff2);
        appointments1_2.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments1_2);

        Appointments appointments1_3 = new Appointments();
        appointments1_3.setDoctors(doctors);
        appointments1_3.setMedicalRecords(medicalRecords);
        appointments1_3.setCustomers(customers);
        appointments1_3.setStatus(false);
        LocalDateTime start1_3 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(16, 0));
        appointments1_3.setStartTime(start1_3);
        appointments1_3.setEndTime(start1_3.plusHours(2));
        appointments1_3.setAnonymous(true);
        appointments1_3.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointments1_3.setStaffs(staff2);
        appointments1_3.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments1_3);

        Appointments appointments2 = new Appointments();
        appointments2.setDoctors(doctors);
        appointments2.setMedicalRecords(medicalRecords);
        appointments2.setCustomers(customers);
        appointments2.setStatus(false);
        LocalDateTime start2 = LocalDateTime.of(LocalDate.of(2025, 8, 2), fixedStartTime);
        appointments2.setStartTime(start2);
        appointments2.setEndTime(start2.plus(duration));
        appointments2.setAnonymous(false);
        appointments2.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointments2.setStaffs(staff);
        appointments2.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments2);

        Appointments appointments2_1 = new Appointments();
        appointments2_1.setDoctors(doctors);
        appointments2_1.setMedicalRecords(medicalRecords);
        appointments2_1.setCustomers(customers);
        appointments2_1.setStatus(false);
        LocalDateTime start2_1 = LocalDateTime.of(LocalDate.of(2025, 8, 2), LocalTime.of(10, 0));
        appointments2_1.setStartTime(start2_1);
        appointments2_1.setEndTime(start2_1.plus(duration));
        appointments2_1.setAnonymous(false);
        appointments2_1.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointments2_1.setStaffs(staff);
        appointments2_1.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments2_1);

        Appointments appointments2_2 = new Appointments();
        appointments2_2.setDoctors(doctors);
        appointments2_2.setMedicalRecords(medicalRecords);
        appointments2_2.setCustomers(customers);
        appointments2_2.setStatus(false);
        LocalDateTime start2_2 = LocalDateTime.of(LocalDate.of(2025, 8, 2), LocalTime.of(14, 0));
        appointments2_2.setStartTime(start2_2);
        appointments2_2.setEndTime(start2_2.plus(duration));
        appointments2_2.setAnonymous(false);
        appointments2_2.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointments2_2.setStaffs(staff2);
        appointments2_2.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments2_2);

        Appointments appointments2_3 = new Appointments();
        appointments2_3.setDoctors(doctors);
        appointments2_3.setMedicalRecords(medicalRecords);
        appointments2_3.setCustomers(customers);
        appointments2_3.setStatus(false);
        LocalDateTime start2_3 = LocalDateTime.of(LocalDate.of(2025, 8, 2), LocalTime.of(16, 0));
        appointments2_3.setStartTime(start2_3);
        appointments2_3.setEndTime(start2_3.plus(duration));
        appointments2_3.setAnonymous(false);
        appointments2_3.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointments2_3.setStaffs(staff2);
        appointments2_3.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments2_3);

        Appointments appointments3 = new Appointments();
        appointments3.setDoctors(doctors);
        appointments3.setMedicalRecords(medicalRecords);
        appointments3.setCustomers(customers);
        appointments3.setStatus(false);
        LocalDateTime start3 = LocalDateTime.of(LocalDate.of(2025, 8, 3), fixedStartTime);
        appointments3.setStartTime(start3);
        appointments3.setEndTime(start3.plus(duration));
        appointments3.setAnonymous(true);
        appointments3.setSchedules(schedulesRepository.findById(3).orElse(null));
        appointments3.setStaffs(staff);
        appointments3.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments3);

        Appointments appointments4 = new Appointments();
        appointments4.setDoctors(doctors);
        appointments4.setMedicalRecords(medicalRecords);
        appointments4.setCustomers(customers);
        appointments4.setStatus(false);
        LocalDateTime start4 = LocalDateTime.of(LocalDate.of(2025, 8, 4), fixedStartTime);
        appointments4.setStartTime(start4);
        appointments4.setEndTime(start4.plus(duration));
        appointments4.setAnonymous(true);
        appointments4.setSchedules(schedulesRepository.findById(4).orElse(null));
        appointments4.setStaffs(staff);
        appointments4.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments4);

        Appointments appointments5 = new Appointments();
        appointments5.setDoctors(doctors);
        appointments5.setMedicalRecords(medicalRecords);
        appointments5.setCustomers(customers);
        appointments5.setStatus(false);
        LocalDateTime start5 = LocalDateTime.of(LocalDate.of(2025, 8, 5), fixedStartTime);
        appointments5.setStartTime(start5);
        appointments5.setEndTime(start5.plus(duration));
        appointments5.setAnonymous(false);
        appointments5.setSchedules(schedulesRepository.findById(5).orElse(null));
        appointments5.setStaffs(staff);
        appointments5.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments5);

        Appointments appointments6 = new Appointments();
        appointments6.setDoctors(doctors);
        appointments6.setMedicalRecords(medicalRecords);
        appointments6.setCustomers(customers);
        appointments6.setStatus(false);
        LocalDateTime start6 = LocalDateTime.of(LocalDate.of(2025, 8, 6), fixedStartTime);
        appointments6.setStartTime(start6);
        appointments6.setEndTime(start6.plus(duration));
        appointments6.setAnonymous(false);
        appointments6.setSchedules(schedulesRepository.findById(6).orElse(null));
        appointments6.setStaffs(staff);
        appointments6.setAppointmentType("Consultation");
        appointmentRepository.save(appointments6);

        Appointments appointments7 = new Appointments();
        appointments7.setDoctors(doctors);
        appointments7.setMedicalRecords(medicalRecords);
        appointments7.setCustomers(customers);
        appointments7.setStatus(false);
        LocalDateTime start7 = LocalDateTime.of(LocalDate.of(2025, 8, 7), fixedStartTime);
        appointments7.setStartTime(start7);
        appointments7.setEndTime(start7.plus(duration));
        appointments7.setAnonymous(true);
        appointments7.setSchedules(schedulesRepository.findById(7).orElse(null));
        appointments7.setStaffs(staff);
        appointments7.setAppointmentType("Consultation");
        appointmentRepository.save(appointments7);

        Appointments appointments8 = new Appointments();
        appointments8.setDoctors(doctors);
        appointments8.setMedicalRecords(medicalRecords);
        appointments8.setCustomers(customers);
        appointments8.setStatus(false);
        LocalDateTime start8 = LocalDateTime.of(LocalDate.of(2025, 8, 8), fixedStartTime);
        appointments8.setStartTime(start8);
        appointments8.setEndTime(start8.plus(duration));
        appointments8.setAnonymous(false);
        appointments8.setSchedules(schedulesRepository.findById(8).orElse(null));
        appointments8.setStaffs(staff);
        appointments8.setAppointmentType("Consultation");
        appointmentRepository.save(appointments8);

        Appointments appointments9 = new Appointments();
        appointments9.setDoctors(doctors);
        appointments9.setMedicalRecords(medicalRecords);
        appointments9.setCustomers(customers);
        appointments9.setStatus(false);
        LocalDateTime start9 = LocalDateTime.of(LocalDate.of(2025, 8, 9), fixedStartTime);
        appointments9.setStartTime(start9);
        appointments9.setEndTime(start9.plus(duration));
        appointments9.setAnonymous(false);
        appointments9.setSchedules(schedulesRepository.findById(9).orElse(null));
        appointments9.setStaffs(staff);
        appointments9.setAppointmentType("Consultation");
        appointmentRepository.save(appointments9);

        Appointments appointments10 = new Appointments();
        appointments10.setDoctors(doctors);
        appointments10.setMedicalRecords(medicalRecords);
        appointments10.setCustomers(customers);
        appointments10.setStatus(false);
        LocalDateTime start10 = LocalDateTime.of(LocalDate.of(2025, 8, 10), fixedStartTime);
        appointments10.setStartTime(start10);
        appointments10.setEndTime(start10.plus(duration));
        appointments10.setAnonymous(true);
        appointments10.setSchedules(schedulesRepository.findById(10).orElse(null));
        appointments10.setStaffs(staff);
        appointments10.setAppointmentType("Consultation");
        appointmentRepository.save(appointments10);

        Appointments appointments11 = new Appointments();
        appointments11.setDoctors(doctors);
        appointments11.setMedicalRecords(medicalRecords);
        appointments11.setCustomers(customers);
        appointments11.setStatus(false);
        LocalDateTime start11 = LocalDateTime.of(LocalDate.of(2025, 8, 11), fixedStartTime);
        appointments11.setStartTime(start11);
        appointments11.setEndTime(start11.plus(duration));
        appointments11.setAnonymous(false);
        appointments11.setSchedules(schedulesRepository.findById(11).orElse(null));
        appointments11.setStaffs(staff);
        appointments11.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments11);

        Appointments appointments12 = new Appointments();
        appointments12.setDoctors(doctors);
        appointments12.setMedicalRecords(medicalRecords);
        appointments12.setCustomers(customers);
        appointments12.setStatus(false);
        LocalDateTime start12 = LocalDateTime.of(LocalDate.of(2025, 8, 12), fixedStartTime);
        appointments12.setStartTime(start12);
        appointments12.setEndTime(start12.plus(duration));
        appointments12.setAnonymous(false);
        appointments12.setSchedules(schedulesRepository.findById(12).orElse(null));
        appointments12.setStaffs(staff);
        appointments12.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments12);

        Appointments appointments13 = new Appointments();
        appointments13.setDoctors(doctors);
        appointments13.setMedicalRecords(medicalRecords);
        appointments13.setCustomers(customers);
        appointments13.setStatus(false);
        LocalDateTime start13 = LocalDateTime.of(LocalDate.of(2025, 8, 13), fixedStartTime);
        appointments13.setStartTime(start13);
        appointments13.setEndTime(start13.plus(duration));
        appointments13.setAnonymous(false);
        appointments13.setSchedules(schedulesRepository.findById(13).orElse(null));
        appointments13.setStaffs(staff);
        appointments13.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments13);

        Appointments appointments14 = new Appointments();
        appointments14.setDoctors(doctors);
        appointments14.setMedicalRecords(medicalRecords);
        appointments14.setCustomers(customers);
        appointments14.setStatus(false);
        LocalDateTime start14 = LocalDateTime.of(LocalDate.of(2025, 8, 14), fixedStartTime);
        appointments14.setStartTime(start14);
        appointments14.setEndTime(start14.plus(duration));
        appointments14.setAnonymous(false);
        appointments14.setSchedules(schedulesRepository.findById(14).orElse(null));
        appointments14.setStaffs(staff);
        appointments14.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments14);

        Appointments appointments15 = new Appointments();
        appointments15.setDoctors(doctors);
        appointments15.setMedicalRecords(medicalRecords);
        appointments15.setCustomers(customers);
        appointments15.setStatus(false);
        LocalDateTime start15 = LocalDateTime.of(LocalDate.of(2025, 8, 15), fixedStartTime);
        appointments15.setStartTime(start15);
        appointments15.setEndTime(start15.plus(duration));
        appointments15.setAnonymous(false);
        appointments15.setSchedules(schedulesRepository.findById(15).orElse(null));
        appointments15.setStaffs(staff);
        appointments15.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments15);


        //Create TreatmentAdd commentMore actions
        TreatmentPlans treatmentPlans = new TreatmentPlans();
        treatmentPlans.setDoctors(doctors);
        treatmentPlans.setArvReqimentID(r1);
        treatmentPlans.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans.setDosageTime(LocalTime.of(8, 0));
        treatmentPlans.setAppointments(appointments1);
        treatmentPlans.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans);

        TreatmentPlans treatmentPlans1 = new TreatmentPlans();
        treatmentPlans1.setDoctors(doctors);
        treatmentPlans1.setArvReqimentID(r2);
        treatmentPlans1.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans1.setDosageTime(LocalTime.of(10, 0));
        treatmentPlans1.setAppointments(appointments2);
        treatmentPlans1.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans1);

        TreatmentPlans treatmentPlans2 = new TreatmentPlans();
        treatmentPlans2.setDoctors(doctors);
        treatmentPlans2.setArvReqimentID(r3);
        treatmentPlans2.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans2.setDosageTime(LocalTime.of(12, 0));
        treatmentPlans2.setAppointments(appointments3);
        treatmentPlans2.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans2);

        TreatmentPlans treatmentPlans3 = new TreatmentPlans();
        treatmentPlans3.setDoctors(doctors);
        treatmentPlans3.setArvReqimentID(r1);
        treatmentPlans3.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans3.setDosageTime(LocalTime.of(14, 0));
        treatmentPlans3.setAppointments(appointments4);
        treatmentPlans3.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans3);

        TreatmentPlans treatmentPlans4 = new TreatmentPlans();
        treatmentPlans4.setDoctors(doctors);
        treatmentPlans4.setArvReqimentID(r1);
        treatmentPlans4.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans4.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans4.setAppointments(appointments5);
        treatmentPlans4.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans4);

        TreatmentPlans treatmentPlans5 = new TreatmentPlans();
        treatmentPlans5.setDoctors(doctors);
        treatmentPlans5.setArvReqimentID(r1);
        treatmentPlans5.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans5.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans5.setAppointments(appointments11);
        treatmentPlans5.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans5);

        TreatmentPlans treatmentPlans6 = new TreatmentPlans();
        treatmentPlans6.setDoctors(doctors);
        treatmentPlans6.setArvReqimentID(r1);
        treatmentPlans6.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans6.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans6.setAppointments(appointments12);
        treatmentPlans6.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans6);

        TreatmentPlans treatmentPlans7 = new TreatmentPlans();
        treatmentPlans7.setDoctors(doctors);
        treatmentPlans7.setArvReqimentID(r1);
        treatmentPlans7.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans7.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans7.setAppointments(appointments13);
        treatmentPlans7.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans7);

        TreatmentPlans treatmentPlans8 = new TreatmentPlans();
        treatmentPlans8.setDoctors(doctors);
        treatmentPlans8.setArvReqimentID(r1);
        treatmentPlans8.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans8.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans8.setAppointments(appointments14);
        treatmentPlans8.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans8);

        TreatmentPlans treatmentPlans9 = new TreatmentPlans();
        treatmentPlans9.setDoctors(doctors);
        treatmentPlans9.setArvReqimentID(r1);
        treatmentPlans9.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans9.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans9.setAppointments(appointments15);
        treatmentPlans9.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans9);


        //Create Test Result
        TestResults testResults = new TestResults();
        testResults.setDoctors(doctors);
        testResults.setCustomers(customers);
        testResults.setAppointments(appointments1);
        testResults.setTreatmentPlan(treatmentPlans);
        testResults.setResultValue(false);
        testResults.setNotes("Notes");
        testResults.setRe_examination(true);
        testResults.setHivViralLoad(69);
        testResults.setCD4(96);
        testResults.setTestType("HIV");
        testResults.setTestDate(appointments1.getStartTime().toLocalDate());
        testResultRepository.save(testResults);

        TestResults testResults1 = new TestResults();
        testResults1.setDoctors(doctors);
        testResults1.setCustomers(customers);
        testResults1.setAppointments(appointments2);
        testResults1.setTreatmentPlan(treatmentPlans1);
        testResults1.setResultValue(true);
        testResults1.setNotes("Notes");
        testResults1.setRe_examination(false);
        testResults1.setHivViralLoad(69);
        testResults1.setCD4(96);
        testResults1.setTestType("HIV");
        testResults1.setTestDate(appointments2.getStartTime().toLocalDate());
        testResultRepository.save(testResults1);

        TestResults testResults2 = new TestResults();
        testResults2.setDoctors(doctors);
        testResults2.setCustomers(customers);
        testResults2.setAppointments(appointments3);
        testResults2.setTreatmentPlan(treatmentPlans2);
        testResults2.setResultValue(true);
        testResults2.setNotes("Notes");
        testResults2.setRe_examination(true);
        testResults2.setHivViralLoad(69);
        testResults2.setCD4(96);
        testResults2.setTestType("HIV");
        testResults2.setTestDate(appointments3.getStartTime().toLocalDate());
        testResultRepository.save(testResults2);

        TestResults testResults3 = new TestResults();
        testResults3.setDoctors(doctors);
        testResults3.setCustomers(customers);
        testResults3.setAppointments(appointments4);
        testResults3.setTreatmentPlan(treatmentPlans3);
        testResults3.setResultValue(false);
        testResults3.setNotes("Notes");
        testResults3.setRe_examination(false);
        testResults3.setHivViralLoad(69);
        testResults3.setCD4(96);
        testResults3.setTestType("HIV");
        testResults3.setTestDate(appointments4.getStartTime().toLocalDate());
        testResultRepository.save(testResults3);

        TestResults testResults4 = new TestResults();
        testResults4.setDoctors(doctors);
        testResults4.setCustomers(customers);
        testResults4.setAppointments(appointments5);
        testResults4.setTreatmentPlan(treatmentPlans4);
        testResults4.setResultValue(true);
        testResults4.setNotes("Notes");
        testResults4.setRe_examination(true);
        testResults4.setHivViralLoad(69);
        testResults4.setCD4(96);
        testResults4.setTestType("HIV");
        testResults4.setTestDate(appointments5.getStartTime().toLocalDate());
        testResultRepository.save(testResults4);

        TestResults testResults5 = new TestResults();
        testResults5.setDoctors(doctors);
        testResults5.setCustomers(customers);
        testResults5.setAppointments(appointments5);
        testResults5.setTreatmentPlan(treatmentPlans5);
        testResults5.setResultValue(true);
        testResults5.setNotes("Notes");
        testResults5.setRe_examination(true);
        testResults5.setHivViralLoad(69);
        testResults5.setCD4(96);
        testResults5.setTestType("HIV");
        testResults5.setTestDate(appointments5.getStartTime().toLocalDate());
        testResultRepository.save(testResults5);

        TestResults testResults6 = new TestResults();
        testResults6.setDoctors(doctors);
        testResults6.setCustomers(customers);
        testResults6.setAppointments(appointments5);
        testResults6.setTreatmentPlan(treatmentPlans6);
        testResults6.setResultValue(true);
        testResults6.setNotes("Notes");
        testResults6.setRe_examination(true);
        testResults6.setHivViralLoad(69);
        testResults6.setCD4(96);
        testResults6.setTestType("HIV");
        testResults6.setTestDate(appointments5.getStartTime().toLocalDate());
        testResultRepository.save(testResults6);

        TestResults testResults7 = new TestResults();
        testResults7.setDoctors(doctors);
        testResults7.setCustomers(customers);
        testResults7.setAppointments(appointments5);
        testResults7.setTreatmentPlan(treatmentPlans7);
        testResults7.setResultValue(true);
        testResults7.setNotes("Notes");
        testResults7.setRe_examination(true);
        testResults7.setHivViralLoad(69);
        testResults7.setCD4(96);
        testResults7.setTestType("HIV");
        testResults7.setTestDate(appointments5.getStartTime().toLocalDate());
        testResultRepository.save(testResults7);

        TestResults testResults8 = new TestResults();
        testResults8.setDoctors(doctors);
        testResults8.setCustomers(customers);
        testResults8.setAppointments(appointments5);
        testResults8.setTreatmentPlan(treatmentPlans8);
        testResults8.setResultValue(true);
        testResults8.setNotes("Notes");
        testResults8.setRe_examination(true);
        testResults8.setHivViralLoad(69);
        testResults8.setCD4(96);
        testResults8.setTestType("HIV");
        testResults8.setTestDate(appointments5.getStartTime().toLocalDate());
        testResultRepository.save(testResults8);

        TestResults testResults9 = new TestResults();
        testResults9.setDoctors(doctors);
        testResults9.setCustomers(customers);
        testResults9.setAppointments(appointments5);
        testResults9.setTreatmentPlan(treatmentPlans9);
        testResults9.setResultValue(true);
        testResults9.setNotes("Notes");
        testResults9.setRe_examination(true);
        testResults9.setHivViralLoad(69);
        testResults9.setCD4(96);
        testResults9.setTestType("HIV");
        testResults9.setTestDate(appointments5.getStartTime().toLocalDate());
        testResultRepository.save(testResults9);



        //Create Reminder Dosage
        Reminders remindersDosage = new Reminders();
        remindersDosage.setCustomers(customers);
        remindersDosage.setReminderTime(treatmentPlans.getDosageTime().atDate(LocalDate.now()));
        remindersDosage.setMessage("uống thuốc");
        remindersDosage.setTestResults(testResults);
        remindersDosage.setAppointments(appointments1);
        remindersDosage.setStaffs(staff);
        remindersDosage.setReminderType("Dosage Reminder");
        remindersDosage.setStatus(true);
        reminderRepository.save(remindersDosage);

        Reminders remindersDosage1 = new Reminders();
        remindersDosage1.setCustomers(customers);
        remindersDosage1.setReminderTime(treatmentPlans1.getDosageTime().atDate(LocalDate.now()));
        remindersDosage1.setMessage("uống thuốc");
        remindersDosage1.setTestResults(testResults1);
        remindersDosage1.setAppointments(appointments2);
        remindersDosage1.setStaffs(staff);
        remindersDosage1.setReminderType("Dosage Reminder");
        remindersDosage1.setStatus(true);
        reminderRepository.save(remindersDosage1);

        Reminders remindersDosage2 = new Reminders();
        remindersDosage2.setCustomers(customers);
        remindersDosage2.setReminderTime(treatmentPlans2.getDosageTime().atDate(LocalDate.now()));
        remindersDosage2.setMessage("uống thuốc");
        remindersDosage2.setTestResults(testResults2);
        remindersDosage2.setAppointments(appointments3);
        remindersDosage2.setStaffs(staff);
        remindersDosage2.setReminderType("Dosage Reminder");
        remindersDosage2.setStatus(true);
        reminderRepository.save(remindersDosage2);

        Reminders remindersDosage3 = new Reminders();
        remindersDosage3.setCustomers(customers);
        remindersDosage3.setReminderTime(treatmentPlans3.getDosageTime().atDate(LocalDate.now()));
        remindersDosage3.setMessage("uống thuốc");
        remindersDosage3.setTestResults(testResults3);
        remindersDosage3.setAppointments(appointments4);
        remindersDosage3.setStaffs(staff);
        remindersDosage3.setReminderType("Dosage Reminder");
        remindersDosage3.setStatus(true);
        reminderRepository.save(remindersDosage3);

        Reminders remindersDosage4 = new Reminders();
        remindersDosage4.setCustomers(customers);
        remindersDosage4.setReminderTime(treatmentPlans4.getDosageTime().atDate(LocalDate.now()));
        remindersDosage4.setMessage("uống thuốc");
        remindersDosage4.setTestResults(testResults4);
        remindersDosage4.setAppointments(appointments5);
        remindersDosage4.setStaffs(staff);
        remindersDosage4.setReminderType("Dosage Reminder");
        remindersDosage4.setStatus(true);
        reminderRepository.save(remindersDosage4);

        Reminders remindersReExam5 = new Reminders();
        remindersReExam5.setCustomers(customers);
        remindersReExam5.setReminderTime(appointments6.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam5.setMessage("tái khám");
        remindersReExam5.setTestResults(testResults5);
        remindersReExam5.setAppointments(appointments6);
        remindersReExam5.setStaffs(staff);
        remindersReExam5.setReminderType("Re-Exam Reminder");
        remindersReExam5.setStatus(true);
        reminderRepository.save(remindersReExam5);

        Reminders remindersReExam6 = new Reminders();
        remindersReExam6.setCustomers(customers);
        remindersReExam6.setReminderTime(appointments7.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam6.setMessage("tái khám");
        remindersReExam6.setTestResults(testResults6);
        remindersReExam6.setAppointments(appointments7);
        remindersReExam6.setStaffs(staff);
        remindersReExam6.setReminderType("Re-Exam Reminder");
        remindersReExam6.setStatus(true);
        reminderRepository.save(remindersReExam6);

        Reminders remindersReExam7 = new Reminders();
        remindersReExam7.setCustomers(customers);
        remindersReExam7.setReminderTime(appointments8.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam7.setMessage("tái khám");
        remindersReExam7.setTestResults(testResults7);
        remindersReExam7.setAppointments(appointments8);
        remindersReExam7.setStaffs(staff);
        remindersReExam7.setReminderType("Re-Exam Reminder");
        remindersReExam7.setStatus(true);
        reminderRepository.save(remindersReExam7);

        Reminders remindersReExam8 = new Reminders();
        remindersReExam8.setCustomers(customers);
        remindersReExam8.setReminderTime(appointments9.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam8.setMessage("tái khám");
        remindersReExam8.setTestResults(testResults8);
        remindersReExam8.setAppointments(appointments9);
        remindersReExam8.setStaffs(staff);
        remindersReExam8.setReminderType("Re-Exam Reminder");
        remindersReExam8.setStatus(true);
        reminderRepository.save(remindersReExam8);

        Reminders remindersReExam9 = new Reminders();
        remindersReExam9.setCustomers(customers);
        remindersReExam9.setReminderTime(appointments10.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam9.setMessage("tái khám");
        remindersReExam9.setTestResults(testResults9);
        remindersReExam9.setAppointments(appointments10);
        remindersReExam9.setStaffs(staff);
        remindersReExam9.setReminderType("Re-Exam Reminder");
        remindersReExam9.setStatus(true);
        reminderRepository.save(remindersReExam9);




        // Example: Add in your createModel() after appointments, doctors, customers are created and saved

        Consultations consultation1 = new Consultations();
        consultation1.setAppointments(appointments6);
        consultation1.setDoctors(doctors);
        consultation1.setCustomers(customers);
        consultation1.setConsultationDate(LocalDate.of(2025, 7, 6));
        consultation1.setNotes("Initial consultation and HIV test.");
        consultationRepository.save(consultation1);

        Consultations consultation2 = new Consultations();
        consultation2.setAppointments(appointments7);
        consultation2.setDoctors(doctors);
        consultation2.setCustomers(customers);
        consultation2.setConsultationDate(LocalDate.of(2025, 7, 7));
        consultation2.setNotes("Follow-up consultation, discussed test results.");
        consultationRepository.save(consultation2);

        Consultations consultation3 = new Consultations();
        consultation3.setAppointments(appointments8);
        consultation3.setDoctors(doctors);
        consultation3.setCustomers(customers);
        consultation3.setConsultationDate(LocalDate.of(2025, 7, 8));
        consultation3.setNotes("Consultation for treatment plan.");
        consultationRepository.save(consultation3);

        Consultations consultation4 = new Consultations();
        consultation4.setAppointments(appointments9);
        consultation4.setDoctors(doctors);
        consultation4.setCustomers(customers);
        consultation4.setConsultationDate(LocalDate.of(2025, 7, 9));
        consultation4.setNotes("Routine check-up and medication review.");
        consultationRepository.save(consultation4);

        Consultations consultation5 = new Consultations();
        consultation5.setAppointments(appointments10);
        consultation5.setDoctors(doctors);
        consultation5.setCustomers(customers);
        consultation5.setConsultationDate(LocalDate.of(2025, 7, 10));
        consultation5.setNotes("Final consultation for this cycle.");
        consultationRepository.save(consultation5);

    }

    @Override
    public void run(String... args) throws Exception {
        List<ArvMedications> meds = medicationRepo.findAll();
        List<ArvRegiments> regiments = regimentRepo.findAll();
        List<Appointments> appointments = appointmentRepository.findAll();
        Managers managers = managerRepository.findById(1).orElse(null);
        Admins admins = adminRepository.findById(1).orElse(null);
        if (meds.size() == 0 && regiments.size() == 0 && appointments.size() == 0 && managers == null && admins == null) {
            createModel();
        }
    }
}