package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

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
        staffUser.setEmail("staff1@staff.com");
        staffUser.setPhone("0444444444");
        staffUser.setFullName("Staff One");
        staffUser.setDateOfBirth(LocalDate.of(1995, 4, 25));
        staffUser.setGender("female");
        staffUser.setRole("Staff");
        staffUser.setCreatedAt(LocalDateTime.now());
        staffUser.setStatus(true);
        userRepository.save(staffUser);

        // Add a customer user
        Users customerUser = new Users();
        customerUser.setUsername("customer");
        customerUser.setPassword(passwordEncoder.encode("customerpass"));
        customerUser.setEmail("customer1@staff.com");
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

        //Create Customer
        Customers customers = new Customers();
        customers.setUsers(customerUser);
        customers.setAddress("Tran Duy Hung");
        customers.setManagers(manager);
        customerRepository.save(customers);

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

        //Create ScheduleAdd commentMore actions
        Schedules schedules = null;
        for (int i = 0; i < 25; i++) {
            schedules = new Schedules();
            schedules.setManagers(manager);
            schedules.setDoctors(doctors);
            schedules.setNotes("Ngày đi làm");
            schedules.setWorkDate(LocalDate.of(2025, 7, 1 + i));
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
        Appointments appointments1 = new Appointments();
        appointments1.setDoctors(doctors);
        appointments1.setMedicalRecords(medicalRecords);
        appointments1.setCustomers(customers);
        appointments1.setStatus(true);
        appointments1.setAppointmentTime(LocalDate.of(2025, 7, 1));
        appointments1.setAnonymous(true);
        appointments1.setSchedules(schedules);
        appointments1.setStaffs(staff);
        appointments1.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments1);

        Appointments appointments2 = new Appointments();
        appointments2.setDoctors(doctors);
        appointments2.setMedicalRecords(medicalRecords);
        appointments2.setCustomers(customers);
        appointments2.setStatus(true);
        appointments2.setAppointmentTime(LocalDate.of(2025, 7, 2));
        appointments2.setAnonymous(false);
        appointments2.setSchedules(schedules);
        appointments2.setStaffs(staff);
        appointments2.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments2);

        Appointments appointments3 = new Appointments();
        appointments3.setDoctors(doctors);
        appointments3.setMedicalRecords(medicalRecords);
        appointments3.setCustomers(customers);
        appointments3.setStatus(true);
        appointments3.setAppointmentTime(LocalDate.of(2025, 7, 3));
        appointments3.setAnonymous(true);
        appointments3.setSchedules(schedules);
        appointments3.setStaffs(staff);
        appointments3.setAppointmentType("Test HIV");
        appointmentRepository.save(appointments3);

        Appointments appointments4 = new Appointments();
        appointments4.setDoctors(doctors);
        appointments4.setMedicalRecords(medicalRecords);
        appointments4.setCustomers(customers);
        appointments4.setStatus(true);
        appointments4.setAppointmentTime(LocalDate.of(2025, 7, 4));
        appointments4.setAnonymous(true);
        appointments4.setSchedules(schedules);
        appointments4.setStaffs(staff);
        appointments4.setAppointmentType("Consultation");
        appointmentRepository.save(appointments4);

        Appointments appointments5 = new Appointments();
        appointments5.setDoctors(doctors);
        appointments5.setMedicalRecords(medicalRecords);
        appointments5.setCustomers(customers);
        appointments5.setStatus(true);
        appointments5.setAppointmentTime(LocalDate.of(2025, 7, 5));
        appointments5.setAnonymous(false);
        appointments5.setSchedules(schedules);
        appointments5.setStaffs(staff);
        appointments5.setAppointmentType("Consultation");
        appointmentRepository.save(appointments5);

        //Create TreatmentAdd commentMore actions
        TreatmentPlans treatmentPlans = new TreatmentPlans();
        treatmentPlans.setDoctors(doctors);
        treatmentPlans.setArvReqimentID(r1);
        treatmentPlans.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans.setDosageTime(LocalTime.of(8, 0));
        treatmentPlans.setAppointments(appointments1);
        treatmentPlansRepository.save(treatmentPlans);

        TreatmentPlans treatmentPlans1 = new TreatmentPlans();
        treatmentPlans1.setDoctors(doctors);
        treatmentPlans1.setArvReqimentID(r2);
        treatmentPlans1.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans1.setDosageTime(LocalTime.of(10, 0));
        treatmentPlans1.setAppointments(appointments2);
        treatmentPlansRepository.save(treatmentPlans1);

        TreatmentPlans treatmentPlans2 = new TreatmentPlans();
        treatmentPlans2.setDoctors(doctors);
        treatmentPlans2.setArvReqimentID(r3);
        treatmentPlans2.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans2.setDosageTime(LocalTime.of(12, 0));
        treatmentPlans2.setAppointments(appointments3);
        treatmentPlansRepository.save(treatmentPlans2);

        TreatmentPlans treatmentPlans3 = new TreatmentPlans();
        treatmentPlans3.setDoctors(doctors);
        treatmentPlans3.setArvReqimentID(r1);
        treatmentPlans3.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans3.setDosageTime(LocalTime.of(14, 0));
        treatmentPlans3.setAppointments(appointments4);
        treatmentPlansRepository.save(treatmentPlans3);

        TreatmentPlans treatmentPlans4 = new TreatmentPlans();
        treatmentPlans4.setDoctors(doctors);
        treatmentPlans4.setArvReqimentID(r1);
        treatmentPlans4.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans4.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans4.setAppointments(appointments5);
        treatmentPlansRepository.save(treatmentPlans4);


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
        testResults.setTestDate(appointments1.getAppointmentTime());
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
        testResults1.setTestDate(appointments2.getAppointmentTime());
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
        testResults2.setTestDate(appointments3.getAppointmentTime());
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
        testResults3.setTestDate(appointments4.getAppointmentTime());
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
        testResults4.setTestDate(appointments5.getAppointmentTime());
        testResultRepository.save(testResults4);



        //Create Reminder Dosage
        Reminders remindersDosage = new Reminders();
        remindersDosage.setCustomers(customers);
        remindersDosage.setReminderTime(treatmentPlans.getDosageTime().atDate(LocalDate.now()));
        remindersDosage.setMessage("uống thuốc");
        remindersDosage.setTestResults(testResults);
        remindersDosage.setAppointments(appointments1);
        remindersDosage.setStaffs(staff);
        remindersDosage.setReminderType("Dosage Reminder");
        reminderRepository.save(remindersDosage);

        Reminders remindersDosage1 = new Reminders();
        remindersDosage1.setCustomers(customers);
        remindersDosage1.setReminderTime(treatmentPlans1.getDosageTime().atDate(LocalDate.now()));
        remindersDosage1.setMessage("uống thuốc");
        remindersDosage1.setTestResults(testResults1);
        remindersDosage1.setAppointments(appointments2);
        remindersDosage1.setStaffs(staff);
        remindersDosage1.setReminderType("Dosage Reminder");
        reminderRepository.save(remindersDosage1);

        Reminders remindersDosage2 = new Reminders();
        remindersDosage2.setCustomers(customers);
        remindersDosage2.setReminderTime(treatmentPlans2.getDosageTime().atDate(LocalDate.now()));
        remindersDosage2.setMessage("uống thuốc");
        remindersDosage2.setTestResults(testResults2);
        remindersDosage2.setAppointments(appointments3);
        remindersDosage2.setStaffs(staff);
        remindersDosage2.setReminderType("Dosage Reminder");
        reminderRepository.save(remindersDosage2);

        Reminders remindersDosage3 = new Reminders();
        remindersDosage3.setCustomers(customers);
        remindersDosage3.setReminderTime(treatmentPlans3.getDosageTime().atDate(LocalDate.now()));
        remindersDosage3.setMessage("uống thuốc");
        remindersDosage3.setTestResults(testResults3);
        remindersDosage3.setAppointments(appointments4);
        remindersDosage3.setStaffs(staff);
        remindersDosage3.setReminderType("Dosage Reminder");
        reminderRepository.save(remindersDosage3);

        Reminders remindersDosage4 = new Reminders();
        remindersDosage4.setCustomers(customers);
        remindersDosage4.setReminderTime(treatmentPlans4.getDosageTime().atDate(LocalDate.now()));
        remindersDosage4.setMessage("uống thuốc");
        remindersDosage4.setTestResults(testResults4);
        remindersDosage4.setAppointments(appointments5);
        remindersDosage4.setStaffs(staff);
        remindersDosage4.setReminderType("Dosage Reminder");
        reminderRepository.save(remindersDosage4);

        // Example: Add in your createModel() after appointments, doctors, customers are created and saved

        Consultations consultation1 = new Consultations();
        consultation1.setAppointments(appointments1);
        consultation1.setDoctors(doctors);
        consultation1.setCustomers(customers);
        consultation1.setConsultationDate(LocalDate.of(2025, 7, 1));
        consultation1.setNotes("Initial consultation and HIV test.");
        consultationRepository.save(consultation1);

        Consultations consultation2 = new Consultations();
        consultation2.setAppointments(appointments2);
        consultation2.setDoctors(doctors);
        consultation2.setCustomers(customers);
        consultation2.setConsultationDate(LocalDate.of(2025, 7, 2));
        consultation2.setNotes("Follow-up consultation, discussed test results.");
        consultationRepository.save(consultation2);

        Consultations consultation3 = new Consultations();
        consultation3.setAppointments(appointments3);
        consultation3.setDoctors(doctors);
        consultation3.setCustomers(customers);
        consultation3.setConsultationDate(LocalDate.of(2025, 7, 3));
        consultation3.setNotes("Consultation for treatment plan.");
        consultationRepository.save(consultation3);

        Consultations consultation4 = new Consultations();
        consultation4.setAppointments(appointments4);
        consultation4.setDoctors(doctors);
        consultation4.setCustomers(customers);
        consultation4.setConsultationDate(LocalDate.of(2025, 7, 4));
        consultation4.setNotes("Routine check-up and medication review.");
        consultationRepository.save(consultation4);

        Consultations consultation5 = new Consultations();
        consultation5.setAppointments(appointments5);
        consultation5.setDoctors(doctors);
        consultation5.setCustomers(customers);
        consultation5.setConsultationDate(LocalDate.of(2025, 7, 5));
        consultation5.setNotes("Final consultation for this cycle.");
        consultationRepository.save(consultation5);

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