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
        manager.setOfficePhone("0912345678");
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
                + "đích đến chấm dứt AIDS vào năm 2030 là khả thi, đặc biệt ở các khu vực Đông – Nam châu Phi. "
                + "Tổ chức này nhấn mạnh rằng việc tiếp cận dịch vụ điều trị, mở rộng chương trình phòng ngừa và giảm kỳ thị "
                + "sẽ đóng vai trò then chốt trong việc kiểm soát sự lây lan của HIV. "
                + "Việc tăng cường xét nghiệm, cung cấp thuốc ARV miễn phí và hỗ trợ cộng đồng dễ bị tổn thương "
                + "đã góp phần làm giảm đáng kể số ca nhiễm mới trong những năm gần đây.");
        blog1.setImageUrl("https://login.medlatec.vn//ImagePath/images/20200418/20200418_virus-hiv.jpg");
        blog1.setSource("VAAC (dịch từ UNAIDS)");
        blog1.setCreateAt(LocalDate.now());
        blogRepository.save(blog1);

        Blogs blog2 = new Blogs();
        blog2.setDoctors(doctors);
        blog2.setTitle("Hội nghị tổng kết hoạt động phòng, chống HIV/AIDS năm 2024");
        blog2.setContent("VAAC đã tổ chức hội nghị tại Đà Nẵng tổng kết năm 2024, đánh giá thành tựu "
                + "và xây dựng kế hoạch tới năm 2025. Tại hội nghị, các đại biểu đã chia sẻ kinh nghiệm triển khai "
                + "các chương trình phòng chống HIV/AIDS tại địa phương, đồng thời đề xuất các giải pháp nâng cao hiệu quả can thiệp. "
                + "Nhiều văn bản pháp lý mới như Nghị định 141/2024/NĐ-CP và Thông tư 26/2024 được đưa ra nhằm tăng cường tính pháp lý "
                + "và đồng bộ trong công tác quản lý và hỗ trợ người nhiễm HIV.");
        blog2.setImageUrl("https://tc.cdnchinhphu.vn/Uploaded/nguyenthilananh/2020_06_15/hiv.png");
        blog2.setSource("VAAC");
        blog2.setCreateAt(LocalDate.now());
        blogRepository.save(blog2);

        Blogs blog3 = new Blogs();
        blog3.setDoctors(doctors);
        blog3.setTitle("Hành trình 21 năm chăm sóc, điều trị HIV/AIDS tại Việt Nam");
        blog3.setContent("Dự án HAIVN/BIDMC đã đồng hành cùng Việt Nam từ năm 2003 trong việc hỗ trợ kỹ thuật, đào tạo nhân lực "
                + "và nâng cao năng lực hệ thống y tế. Qua 21 năm, dự án đã đào tạo hơn 10.000 nhân viên y tế, "
                + "triển khai nhiều chương trình đào tạo liên tục, cải tiến mô hình điều trị HIV và thúc đẩy chăm sóc toàn diện. "
                + "Nhờ những nỗ lực bền bỉ này, số người được điều trị HIV đã tăng từ khoảng 1.000 vào đầu những năm 2000 lên hơn 170.000 người vào năm 2023, "
                + "góp phần kiểm soát dịch và nâng cao chất lượng cuộc sống cho người nhiễm HIV tại Việt Nam.");
        blog3.setImageUrl("https://media.vov.vn/sites/default/files/styles/large/public/2022-09-2/493ss_getty_rm_hiv_virus.jpg");
        blog3.setSource("VAAC");
        blog3.setCreateAt(LocalDate.now());
        blogRepository.save(blog3);

        Blogs blog4 = new Blogs();
        blog4.setDoctors(doctors);
        blog4.setTitle("K=K: Không phát hiện = Không lây truyền");
        blog4.setContent("Chiến dịch K=K (Undetectable = Untransmittable) được công nhận rộng rãi trên toàn cầu, "
                + "khẳng định rằng người sống chung với HIV có tải lượng virus dưới ngưỡng phát hiện và duy trì điều trị ARV đều đặn "
                + "không thể lây truyền HIV qua đường tình dục. Thông điệp này không chỉ mang ý nghĩa y học mà còn phá vỡ rào cản kỳ thị, "
                + "giúp người nhiễm HIV tự tin hơn trong cuộc sống và các mối quan hệ. K=K là bước ngoặt trong nỗ lực thúc đẩy quyền bình đẳng "
                + "và tăng cường tiếp cận dịch vụ chăm sóc sức khỏe toàn diện cho người nhiễm HIV.");
        blog4.setImageUrl("https://matquocte.vn/wp-content/uploads/2024/08/827-Virut-HIV-600x400.jpeg");
        blog4.setSource("VAAC");
        blog4.setCreateAt(LocalDate.now());
        blogRepository.save(blog4);

        Blogs blog5 = new Blogs();
        blog5.setDoctors(doctors);
        blog5.setTitle("Vai trò của người sống chung với HIV");
        blog5.setContent("Người nhiễm HIV không chỉ là đối tượng nhận hỗ trợ mà còn là những cá nhân có thể góp phần tích cực "
                + "vào công tác phòng chống HIV/AIDS. Bằng sự chia sẻ kinh nghiệm sống, họ có thể tham gia các chương trình truyền thông, "
                + "đào tạo đồng đẳng và tư vấn hỗ trợ cộng đồng. Việc trao quyền và tạo cơ hội cho họ tham gia vào hệ thống y tế, "
                + "tổ chức xã hội không chỉ giúp giảm kỳ thị mà còn thúc đẩy việc tiếp cận sớm điều trị và duy trì tuân thủ thuốc ARV hiệu quả. "
                + "Đây là chiến lược then chốt trong phòng chống HIV/AIDS bền vững.");
        blog5.setImageUrl("https://tc.cdnchinhphu.vn/Uploaded/nguyengiangoanh/2018_05_28/HIV%20gay%20ra%20trong%20nao.png");
        blog5.setSource("VAAC");
        blog5.setCreateAt(LocalDate.now());
        blogRepository.save(blog5);

        // 10 blogs mẫu mới có nguồn đáng tin cậy

        Blogs blog6 = new Blogs();
        blog6.setDoctors(doctors);
        blog6.setTitle("Quỹ quốc tế cảnh báo thiếu hụt tài chính HIV/AIDS năm 2025");
        blog6.setContent("Theo Báo cáo toàn cầu 2025 của UNAIDS, cortes nguồn viện trợ quốc tế, đặc biệt từ Mỹ (PEPFAR), "
                + "đã khiến hơn 60% chương trình phòng ngừa HIV ở các nước thu nhập thấp bị gián đoạn. Dự báo sẽ có thêm "
                + "6 triệu ca nhiễm mới và 4 triệu người tử vong vì AIDS trước năm 2029 nếu không có biện pháp khắc phục. "
                + "UNAIDS kêu gọi chính phủ và khu vực tư nhân tăng ngân sách y tế để tránh đổ vỡ hệ thống chăm sóc sức khoẻ toàn cầu.");
        blog6.setImageUrl("https://matsaigondalat.com/wp-content/uploads/2023/12/2.jpg");
        blog6.setSource("UNAIDS (Global AIDS Update 2025)");
        blog6.setCreateAt(LocalDate.now());
        blogRepository.save(blog6);

        Blogs blog7 = new Blogs();
        blog7.setDoctors(doctors);
        blog7.setTitle("Luật hình sự hóa LGBTQ+ làm chậm tiến trình chấm dứt AIDS");
        blog7.setContent("Báo cáo của UNAIDS cảnh báo các luật đàn áp LGBTQ+ tại nhiều quốc gia như Uganda, Ghana… "
                + "đã tạo ra rào cản với các dân số dễ bị tổn thương, khiến xét nghiệm và điều trị giảm mạnh. "
                + "Mặc dù số ca nhiễm và tử vong năm 2024 là thấp nhất trong 30 năm qua, nhưng các luật mới có thể "
                + "tăng 6 triệu ca nhiễm và 4 triệu tử vong vào năm 2029 nếu không khắc phục.");
        blog7.setImageUrl("https://www.vinmec.com/static/uploads/20190624_093406_101047_HIV_max_1800x1800_jpg_efd3072742.jpg");
        blog7.setSource("The Guardian (dịch từ UNAIDS)");
        blog7.setCreateAt(LocalDate.now());
        blogRepository.save(blog7);

        Blogs blog8 = new Blogs();
        blog8.setDoctors(doctors);
        blog8.setTitle("Sụt giảm nhân sự UNAIDS đe dọa hệ thống phòng HIV toàn cầu");
        blog8.setContent("UNAIDS đã cắt giảm hơn 50% nhân sự (từ 661 xuống còn 294 người) do khủng hoảng tài chính toàn cầu. "
                + "Điều này khiến nhiều chương trình giám sát, xét nghiệm và hỗ trợ cộng đồng bị đình trệ giữa lúc sự gia tăng "
                + "các ca nhiễm mới. Các chuyên gia cảnh báo đây là 'đe dọa nghiêm trọng' đối với nỗ lực kết thúc AIDS vào năm 2030.");
        blog8.setImageUrl("https://special.vietnamplus.vn/wp-content/uploads/2021/03/stophiv-1608633656-45.jpg");
        blog8.setSource("Economic Times (dịch từ UNAIDS)");
        blog8.setCreateAt(LocalDate.now());
        blogRepository.save(blog8);

        Blogs blog9 = new Blogs();
        blog9.setDoctors(doctors);
        blog9.setTitle("Làn sóng thiếu hụt phòng ngừa HIV tại các nước nghèo");
        blog9.setContent("Reuters dẫn báo cáo cho biết dù 25/60 nước thu nhập thấp đã tăng chi nội địa cho HIV, "
                + "nhưng vẫn không đủ bù đắp sau khi viện trợ giảm mạnh. Nhiều phòng khám HIV ở các khu vực có nguy cơ cao "
                + "đã đóng cửa do thiếu nhân lực và thuốc. UNAIDS nhấn mạnh 'phòng ngừa bị ảnh hưởng nặng hơn điều trị'.");
        blog9.setImageUrl("https://tc.cdnchinhphu.vn/346625049939054592/2023/12/1/thumb169-1606789553780539573669-16698871636881947875539-17013678290171130962216.jpg");
        blog9.setSource("Reuters");
        blog9.setCreateAt(LocalDate.now());
        blogRepository.save(blog9);

        Blogs blog10 = new Blogs();
        blog10.setDoctors(doctors);
        blog10.setTitle("Sống còn của chương trình PEPFAR tại Mỹ");
        blog10.setContent("AP News cảnh báo nếu Mỹ không duy trì mức cam kết 4 tỷ USD/năm cho PEPFAR, "
                + "việc gián đoạn nhanh chóng tại nhiều nước châu Phi có thể dẫn đến đóng cửa hàng nghìn trạm y tế "
                + "và gián đoạn cung cấp thuốc ARV. Điều này có thể tạo ra ‘tác động hệ thống’ nguy hiểm cho toàn cầu.");
        blog10.setImageUrl("https://baohiemxahoi.gov.vn:4545/pic/new_BHXH/Khac/creative-market_aids-posters-_jennylipets-_20180111022952PM.jpg");
        blog10.setSource("AP News");
        blog10.setCreateAt(LocalDate.now());
        blogRepository.save(blog10);

        Blogs blog11 = new Blogs();
        blog11.setDoctors(doctors);
        blog11.setTitle("Ngày HIV/AIDS Thế giới 2024: Việt Nam làm mẫu mực");
        blog11.setContent("Theo VietnamPlus, tính đến cuối 2023 Việt Nam có ~183.000 người đang dùng ARV, với hơn 97% "
                + "đạt tải lượng virus‑không phát hiện—đứng đầu khu vực châu Á–Thái Bình Dương. Chương trình PrEP cũng đạt "
                + "hiệu quả tới 97%. Hàng năm có gần 2 triệu lượt xét nghiệm và hơn 11.000 ca nhiễm mới được phát hiện.");
        blog11.setImageUrl("https://cdcbentre.org/uploads/news/2023_12/unnamed-14.jpg");
        blog11.setSource("VietnamPlus");
        blog11.setCreateAt(LocalDate.now());
        blogRepository.save(blog11);

        Blogs blog12 = new Blogs();
        blog12.setDoctors(doctors);
        blog12.setTitle("Việt Nam triển khai hệ thống HIV INFO 4.0 toàn quốc");
        blog12.setContent("PATH hỗ trợ VAAC triển khai hệ thống HIV INFO 4.0 từ 2022, đến cuối 2023 đã bao phủ 100% tuyến tỉnh "
                + "và khoảng 85% xã/phường. Hệ thống giúp loại trùng dữ liệu, nâng cao chất lượng quản lý bệnh nhân (~370.000 hồ sơ). "
                + "VAAC đánh giá đây là top 10 thành tựu quan trọng nhất năm 2022.");
        blog12.setImageUrl("https://e.khoahoc.tv/photos/image/2015/02/02/virus-hiv-2.jpg");
        blog12.setSource("PATH / VAAC");
        blog12.setCreateAt(LocalDate.now());
        blogRepository.save(blog12);

        Blogs blog13 = new Blogs();
        blog13.setDoctors(doctors);
        blog13.setTitle("Mẹ và bé: tiến độ 84% tiếp cận điều trị ARV toàn cầu");
        blog13.setContent("Theo số liệu của UNAIDS/WHO 2025, toàn cầu 84% phụ nữ mang thai nhiễm HIV đã được dùng ARV để phòng lây truyền mẹ–con. "
                + "Tuy nhiên cần thêm 3,2 triệu người biết tình trạng của họ để đạt mục tiêu 95–95–95 vào năm 2025.");
        blog13.setImageUrl("https://thptluongthevinhdienbien.edu.vn/upload/46660/20210806/1_9f36fc4ca4.jpg");
        blog13.setSource("WHO / UNAIDS");
        blog13.setCreateAt(LocalDate.now());
        blogRepository.save(blog13);

        Blogs blog14 = new Blogs();
        blog14.setDoctors(doctors);
        blog14.setTitle("Triển vọng thuốc tiêm Yeztugo phòng HIV 100%");
        blog14.setContent("Theo AP News, FDA Mỹ đã phê duyệt thuốc tiêm Yeztugo của Gilead, với hiệu quả 100% trong phòng ngừa HIV "
                + "ở các nhóm nguy cơ cao. Tuy nhiên giá cao và hạn chế bản quyền phổ cập khiến nhiều nước khó tiếp cận. "
                + "Gilead cho biết sẽ cấp phép nguyên bản tại 120 nước có HIV cao nhưng chưa bao gồm nhiều nước Latin.");
        blog14.setImageUrl("https://tc.cdnchinhphu.vn/Uploaded/nguyenthilananh/2015_08_26/hi.jpg");
        blog14.setSource("AP News");
        blog14.setCreateAt(LocalDate.now());
        blogRepository.save(blog14);

        Blogs blog15 = new Blogs();
        blog15.setDoctors(doctors);
        blog15.setTitle("Thống kê toàn cầu HIV/AIDS năm 2024");
        blog15.setContent("Theo Fact‑sheet của UNAIDS 2025: toàn cầu có khoảng 40,8 triệu người nhiễm HIV, 1,3 triệu ca nhiễm mới "
                + "và 630.000 ca tử vong do AIDS trong năm 2024. Tỉ lệ điều trị ARV đạt 77% người nhiễm, tuy nhiên vẫn còn khoảng "
                + "5,3 triệu người chưa biết tình trạng nhiễm trùng của mình.");
        blog15.setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fbaochinhphu.vn%2Fbao-chi-can-uu-tien-tuyen-truyen-phong-chong-hiv-aids-10237373.htm&psig=AOvVaw2gzrQwxcZegGqSg0ByQv3-&ust=1752501549972000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIi2-pr_uY4DFQAAAAAdAAAAABAf");
        blog15.setSource("UNAIDS Fact Sheet 2025");
        blog15.setCreateAt(LocalDate.now());
        blogRepository.save(blog15);


        Materials material1 = new Materials();
        material1.setDoctor(doctors);
        material1.setTitle("Kiến thức cơ bản về HIV/AIDS");
        material1.setContent("Tài liệu cung cấp các kiến thức nền tảng bao gồm định nghĩa HIV và AIDS, sự khác biệt giữa hai khái niệm, "
                + "các con đường lây truyền phổ biến như máu, quan hệ tình dục không an toàn, và từ mẹ sang con. "
                + "Ngoài ra, tài liệu cũng trình bày triệu chứng ban đầu của người nhiễm HIV và hướng dẫn các biện pháp phòng tránh hiệu quả, "
                + "đặc biệt nhấn mạnh vai trò của sử dụng bao cao su, không dùng chung kim tiêm và xét nghiệm định kỳ.");
        material1.setImageUrl("https://lh3.googleusercontent.com/proxy/RY0iS-K4R_K7GDttuqbvonFzrqHbQW5QHgaEfIzY5viSbfvVCmMXeGBY0m_v-8IBjHcvrhCRkfPJ0Qbctg_qf8E6IR9Zvx4pkkXZpaDOpOujE3nesDHUaVSVhFWS-51WGy0");
        material1.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material1.setCreateAt(LocalDate.now());
        materialRepository.save(material1);

        Materials material2 = new Materials();
        material2.setDoctor(doctors);
        material2.setTitle("Đào tạo kỹ thuật xét nghiệm HIV");
        material2.setContent("Tài liệu hướng dẫn chi tiết các bước trong quy trình xét nghiệm HIV từ lấy mẫu, xử lý, đọc kết quả đến báo cáo. "
                + "Ngoài ra, còn bao gồm các nguyên tắc đảm bảo an toàn sinh học trong phòng xét nghiệm, "
                + "kiểm soát nhiễm khuẩn chéo và hướng dẫn hiệu chuẩn thiết bị định kỳ nhằm đảm bảo độ chính xác kết quả. "
                + "Đây là tài liệu bắt buộc cho cán bộ xét nghiệm tại các tuyến y tế cơ sở và bệnh viện.");
        material2.setImageUrl("https://lh3.googleusercontent.com/proxy/9lW4OAWFttL6GbeQNitsx4zHa2asA3kKKGUUiu7RPLLdl_M0IG5uWCk1vYWMq4Z2WeRM2vQN2Q3J2_1ukAlA4p9G1i1Pb-4UkbTR9aGsZhMrrtk1wegzq3PXxLEteUZ5w3_IUj5tkaA");
        material2.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material2.setCreateAt(LocalDate.now());
        materialRepository.save(material2);

        Materials material3 = new Materials();
        material3.setDoctor(doctors);
        material3.setTitle("HIV kháng thuốc: Sổ tay chuyên môn");
        material3.setContent("Sổ tay chuyên môn giúp cán bộ y tế nhận biết các dấu hiệu thất bại điều trị HIV, "
                + "chẩn đoán tình trạng kháng thuốc thông qua tải lượng virus và xét nghiệm kháng gen. "
                + "Tài liệu cũng hướng dẫn chuyển phác đồ ARV, theo dõi đáp ứng và tư vấn hỗ trợ người bệnh trong quá trình điều trị lâu dài. "
                + "Đặc biệt nhấn mạnh đến tính tuân thủ điều trị – yếu tố then chốt giúp ngăn ngừa tình trạng kháng thuốc.");
        material3.setImageUrl("https://www.google.com/url?sa=i&url=http%3A%2F%2Fthcs-hchu.huongtra.thuathienhue.edu.vn%2Fprintpage-tin-tuc%2Fbai-tuyen-truyen-phongchong-hiv-aids.html&psig=AOvVaw3EOPqKjkEQCUjKogHExtSf&ust=1752501666805000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOj76cz_uY4DFQAAAAAdAAAAABA7");
        material3.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material3.setCreateAt(LocalDate.now());
        materialRepository.save(material3);

        Materials material4 = new Materials();
        material4.setDoctor(doctors);
        material4.setTitle("Hướng dẫn triển khai BHYT cho người nhiễm HIV");
        material4.setContent("Tài liệu hướng dẫn chi tiết cách thức người nhiễm HIV tiếp cận và sử dụng bảo hiểm y tế (BHYT), "
                + "bao gồm quy trình đăng ký, duy trì, và quyền lợi trong khám chữa bệnh. "
                + "Nhấn mạnh vai trò của BHYT trong bảo đảm tài chính khi điều trị ARV lâu dài, xét nghiệm tải lượng virus và điều trị bệnh kèm theo. "
                + "Tài liệu còn cập nhật các chính sách mới liên quan đến thanh toán BHYT theo Thông tư 27/2022/TT-BYT.");
        material4.setImageUrl("https://nhidong.org.vn/UploadImages/bvnhidong/PHN11/2018_12/20/hinh2.jpg");
        material4.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material4.setCreateAt(LocalDate.now());
        materialRepository.save(material4);

        Materials material5 = new Materials();
        material5.setDoctor(doctors);
        material5.setTitle("Thuốc kháng HIV (ARV)");
        material5.setContent("Tài liệu giới thiệu các nhóm thuốc ARV chính như ức chế men sao chép ngược, ức chế protease và ức chế tích hợp. "
                + "Trình bày cơ chế tác dụng của từng nhóm thuốc, lợi ích trong giảm tải lượng virus, phục hồi miễn dịch và ngăn ngừa lây truyền. "
                + "Ngoài ra còn cung cấp hướng dẫn dùng thuốc đúng cách, xử lý tác dụng phụ thường gặp và tư vấn nâng cao tuân thủ điều trị.");
        material5.setImageUrl("https://lh3.googleusercontent.com/proxy/zOzX4W5_avT0r4DdHvkIfNh_8FrgsrKlCKYHVQ6J7U0TR7jzbKqrDmU3O52BBs7xrQpHEotqIwiX2W1b6HNyPP1z6cSBaAleL_VHOw98RfyW9SefVgOx");
        material5.setSource("VAAC - Cục Phòng chống HIV/AIDS");
        material5.setCreateAt(LocalDate.now());
        materialRepository.save(material5);

        // 10 tài liệu mở rộng với nguồn chính thống

        Materials material6 = new Materials();
        material6.setDoctor(doctors);
        material6.setTitle("Hướng dẫn điều trị PrEP – Phòng nhiễm HIV trước phơi nhiễm");
        material6.setContent("Tài liệu của WHO và VAAC cung cấp hướng dẫn chi tiết về sử dụng PrEP: đối tượng chỉ định (nhóm nguy cơ cao), liều dùng hàng ngày, "
                + "quy trình theo dõi sau mỗi 3 tháng, đồng thời xử lý các vấn đề liên quan đến tuân thủ và tác dụng phụ. "
                + "Phân tích hiệu quả 99% khi dùng đều và hướng dẫn tư vấn để nâng cao tiếp cận cộng đồng.");
        material6.setImageUrl("https://png.pngtree.com/png-clipart/20230110/original/pngtree-medical-aids-knowledge-publicity-leaflet-png-image_8897858.png");
        material6.setSource("WHO / VAAC");
        material6.setCreateAt(LocalDate.now());
        materialRepository.save(material6);

        Materials material7 = new Materials();
        material7.setDoctor(doctors);
        material7.setTitle("Hướng dẫn sử dụng PEP – Xử lý sau phơi nhiễm HIV");
        material7.setContent("Tài liệu CDC và PATH hướng dẫn chi tiết cách sử dụng PEP (Post-Exposure Prophylaxis): "
                + "thời điểm bắt đầu <72h sau phơi nhiễm, phác đồ 28 ngày, theo dõi chức năng gan/thận trong 2–4 tuần đầu. "
                + "Cung cấp flowchart xử lý tình huống với các nhóm nguy cơ khác nhau (hôn, quan hệ không bảo hộ, kim tiêm, vết thương...).");
        material7.setImageUrl("https://phapluat.tuoitrethudo.com.vn/stores/news_dataimages/dinhthithuylinh/052019/28/07/in_article/trien-khai-thang-cao-diem-du-phong-lay-truyen-hiv-tu-me-sang-con-nam-2019_5.jpg");
        material7.setSource("CDC / PATH");
        material7.setCreateAt(LocalDate.now());
        materialRepository.save(material7);

        Materials material8 = new Materials();
        material8.setDoctor(doctors);
        material8.setTitle("Chăm sóc sức khỏe tâm lý người sống chung với HIV");
        material8.setContent("Do WHO và PATH phát triển, tài liệu đề cập sức khỏe tâm thần – xã hội của người nhiễm HIV: วิธี tư vấn giảm STRESS, "
                + "chống trầm cảm, kỹ năng xây dựng hệ hỗ trợ xã hội. "
                + "Bao gồm biểu mẫu đánh giá nhanh tâm lý và hướng dẫn referral đến chuyên gia khi cần thiết.");
        material8.setImageUrl("https://tudu.com.vn/data/2011/11/09/14150206_to%20gap%201a.jpg");
        material8.setSource("WHO / PATH");
        material8.setCreateAt(LocalDate.now());
        materialRepository.save(material8);

        Materials material9 = new Materials();
        material9.setDoctor(doctors);
        material9.setTitle("Hướng dẫn điều trị nhiễm khuẩn cơ hội ở người HIV");
        material9.setContent("Tài liệu CDC tổng hợp chuẩn chẩn đoán và điều trị các nhiễm khuẩn cơ hội phổ biến: lao, viêm phổi do Pneumocystis, nhiễm vi nấm Cryptococcus… "
                + "Bao gồm phác đồ thuốc, liều dùng ARV hợp lý trong từng giai đoạn nhiễm khuẩn và cách phối hợp điều trị để tránh tương tác thuốc.");
        material9.setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fcdc.ytetayninh.vn%2Ftre-em-nhiem-hiv-nhung-van-de-can-biet%2F&psig=AOvVaw2rMSk_GNncxgdDoa0PX-zU&ust=1752501789175000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPDboIiAuo4DFQAAAAAdAAAAABBl");
        material9.setSource("CDC");
        material9.setCreateAt(LocalDate.now());
        materialRepository.save(material9);

        Materials material10 = new Materials();
        material10.setDoctor(doctors);
        material10.setTitle("Phân tích đề kháng gen HIV – Hướng dẫn phòng ngừa");
        material10.setContent("Tài liệu WHO nâng cao hướng dẫn lấy mẫu, giải trình tự gen để phát hiện biến chủng kháng thuốc. "
                + "Cung cấp cách phân tích kết quả, ứng dụng trong điều chỉnh phác đồ ARV cá thể hóa, giảm nguy cơ thất bại điều trị.");
        material10.setImageUrl("https://soyte.hatinh.gov.vn/upload/1000030/fck/files/738_e5871.jpg");
        material10.setSource("WHO");
        material10.setCreateAt(LocalDate.now());
        materialRepository.save(material10);

        Materials material11 = new Materials();
        material11.setDoctor(doctors);
        material11.setTitle("Giáo dục tình dục toàn diện để phòng HIV cho thanh thiếu niên");
        material11.setContent("Tài liệu PATH phối hợp với VAAC phát triển bài giảng, hoạt động nhóm, video, brochure nhằm giáo dục giới trẻ: "
                + "quyền, trách nhiệm, kiến thức, kỹ năng từ chối, sử dụng bao cao su, PrEP/PEP và cách tiếp cận dịch vụ y tế thân thiện với tuổi teen.");
        material11.setImageUrl("https://lh4.googleusercontent.com/proxy/o0BcH3ZtMmSU-7Sug3muiSkm4mqhIx4T9PWqoaxtEPvAqmq57Y1nLW3cppW1eS4x-Q09DvU0wewPjIXtL35grS4I7kPdEOk43PYE5TjhinTqUzIZu0oUR_WhE4U9ZiQp");
        material11.setSource("PATH / VAAC");
        material11.setCreateAt(LocalDate.now());
        materialRepository.save(material11);

        Materials material12 = new Materials();
        material12.setDoctor(doctors);
        material12.setTitle("Quản lý dữ liệu HIV bằng hệ thống HIS-RHIS");
        material12.setContent("Tài liệu PATH và VAAC cung cấp hướng dẫn dùng hệ thống quản lý thông tin y tế HIS-RHIS: "
                + "quy trình nhập liệu, bảo mật thông tin bệnh nhân HIV, tổng hợp báo cáo quốc gia và phân tích dữ liệu để ra quyết định chính sách.");
        material12.setImageUrl("https://hellodoctors.vn/img/uploads/images/6-dieu-nguoi-nhiem-hiv-can-biet-de-phong-tranh-lay-truyen-benh1.jpg");
        material12.setSource("PATH / VAAC");
        material12.setCreateAt(LocalDate.now());
        materialRepository.save(material12);

        Materials material13 = new Materials();
        material13.setDoctor(doctors);
        material13.setTitle("Phòng chống HIV trong cộng đồng chuyển giới");
        material13.setContent("Tài liệu của WHO tập trung vào nhóm chuyển giới – dân số có tỷ lệ nhiễm cao: sử dụng PrEP, K=K, xét nghiệm định kỳ, "
                + "tư vấn tâm lý – xã hội phù hợp. Hướng dẫn đào tạo cán bộ y tế thân thiện, giảm kỳ thị khi tiếp cận dịch vụ.");
        material13.setImageUrl("https://i.imgur.com/placeholder23.jpg");
        material13.setSource("WHO");
        material13.setCreateAt(LocalDate.now());
        materialRepository.save(material13);

        Materials material14 = new Materials();
        material14.setDoctor(doctors);
        material14.setTitle("Ứng dụng Telehealth trong điều trị HIV tại Việt Nam");
        material14.setContent("Tài liệu PATH & VAAC phát triển mô hình tư vấn, theo dõi điều trị từ xa: app, cuộc gọi video, nhập liệu từ xa. "
                + "Phân tích kết quả giảm tải cho hệ thống và cải thiện sự tiếp cận chăm sóc cho bệnh nhân ở vùng sâu vùng xa.");
        material14.setImageUrl("http://phuyencdc.vn/upload/1002842/20210604/HIV-AIDS_LAY_TRUYEN_NHU_THE_NAO_2_37c5c61651___PNG_f9bc711e5a.png");
        material14.setSource("PATH / VAAC");
        material14.setCreateAt(LocalDate.now());
        materialRepository.save(material14);

        Materials material15 = new Materials();
        material15.setDoctor(doctors);
        material15.setTitle("Hướng dẫn giảm hơn 30% tử vong do AIDS – Kinh nghiệm quốc tế");
        material15.setContent("Tài liệu WHO trình bày các can thiệp hiệu quả: tầm soát sớm, điều trị ARV, phòng nhiễm khuẩn cơ hội, "
                + "tăng cường chăm sóc tâm lý, hỗ trợ xã hội… dựa trên các chương trình mẫu từ Nam Phi, Việt Nam, Uganda. "
                + "Mục tiêu giảm “tử vong AIDS” xuống dưới 200.000 ca/năm vào năm 2030.");
        material15.setImageUrl("https://ksbtdanang.vn/uploads/chuyen-mon/2021_06/0706212.jpg");
        material15.setSource("WHO");
        material15.setCreateAt(LocalDate.now());
        materialRepository.save(material15);


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
        for (int i = 0; i < 25; i++) {
            schedules = new Schedules();
            schedules.setManagers(manager);
            schedules.setDoctors(doctors);
            schedules.setNotes("Ngày đi làm");
            schedules.setWorkDate(LocalDate.of(2025, 9, 1 + i));
            schedulesRepository.save(schedules);
        }
        for (int i = 0; i < 25; i++) {
            schedules = new Schedules();
            schedules.setManagers(manager);
            schedules.setDoctors(doctors);
            schedules.setNotes("Ngày đi làm");
            schedules.setWorkDate(LocalDate.of(2025, 10, 1 + i));
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

        Appointments appointment1 = new Appointments();
        appointment1.setDoctors(doctors);
        appointment1.setMedicalRecords(medicalRecords);
        appointment1.setCustomers(customers);
        appointment1.setStatus(false);
        LocalDateTime start1 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(8, 0));
        appointment1.setStartTime(start1);
        appointment1.setEndTime(start1.plusHours(2));
        appointment1.setAnonymous(true);
        appointment1.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointment1.setStaffs(staff);
        appointment1.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment1);

        Appointments appointment2 = new Appointments();
        appointment2.setDoctors(doctors);
        appointment2.setMedicalRecords(medicalRecords);
        appointment2.setCustomers(customers);
        appointment2.setStatus(false);
        LocalDateTime start2 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(10, 0));
        appointment2.setStartTime(start2);
        appointment2.setEndTime(start2.plusHours(2));
        appointment2.setAnonymous(true);
        appointment2.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointment2.setStaffs(staff);
        appointment2.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment2);

        Appointments appointment3 = new Appointments();
        appointment3.setDoctors(doctors);
        appointment3.setMedicalRecords(medicalRecords);
        appointment3.setCustomers(customers);
        appointment3.setStatus(false);
        LocalDateTime start3 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(14, 0));
        appointment3.setStartTime(start3);
        appointment3.setEndTime(start3.plusHours(2));
        appointment3.setAnonymous(true);
        appointment3.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointment3.setStaffs(staff2);
        appointment3.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment3);

        Appointments appointment4 = new Appointments();
        appointment4.setDoctors(doctors);
        appointment4.setMedicalRecords(medicalRecords);
        appointment4.setCustomers(customers);
        appointment4.setStatus(false);
        LocalDateTime start4 = LocalDateTime.of(LocalDate.of(2025, 8, 1), LocalTime.of(16, 0));
        appointment4.setStartTime(start4);
        appointment4.setEndTime(start4.plusHours(2));
        appointment4.setAnonymous(true);
        appointment4.setSchedules(schedulesRepository.findById(1).orElse(null));
        appointment4.setStaffs(staff2);
        appointment4.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment4);

        Appointments appointment5 = new Appointments();
        appointment5.setDoctors(doctors);
        appointment5.setMedicalRecords(medicalRecords);
        appointment5.setCustomers(customers);
        appointment5.setStatus(false);
        LocalDateTime start5 = LocalDateTime.of(LocalDate.of(2025, 8, 2), fixedStartTime);
        appointment5.setStartTime(start5);
        appointment5.setEndTime(start5.plus(duration));
        appointment5.setAnonymous(false);
        appointment5.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointment5.setStaffs(staff);
        appointment5.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment5);

        Appointments appointment6 = new Appointments();
        appointment6.setDoctors(doctors);
        appointment6.setMedicalRecords(medicalRecords);
        appointment6.setCustomers(customers);
        appointment6.setStatus(false);
        LocalDateTime start6 = LocalDateTime.of(LocalDate.of(2025, 8, 2), LocalTime.of(10, 0));
        appointment6.setStartTime(start6);
        appointment6.setEndTime(start6.plus(duration));
        appointment6.setAnonymous(false);
        appointment6.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointment6.setStaffs(staff);
        appointment6.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment6);

        Appointments appointment7 = new Appointments();
        appointment7.setDoctors(doctors);
        appointment7.setMedicalRecords(medicalRecords);
        appointment7.setCustomers(customers);
        appointment7.setStatus(false);
        LocalDateTime start7 = LocalDateTime.of(LocalDate.of(2025, 8, 2), LocalTime.of(14, 0));
        appointment7.setStartTime(start7);
        appointment7.setEndTime(start7.plus(duration));
        appointment7.setAnonymous(false);
        appointment7.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointment7.setStaffs(staff2);
        appointment7.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment7);

        Appointments appointment8 = new Appointments();
        appointment8.setDoctors(doctors);
        appointment8.setMedicalRecords(medicalRecords);
        appointment8.setCustomers(customers);
        appointment8.setStatus(false);
        LocalDateTime start8 = LocalDateTime.of(LocalDate.of(2025, 8, 2), LocalTime.of(16, 0));
        appointment8.setStartTime(start8);
        appointment8.setEndTime(start8.plus(duration));
        appointment8.setAnonymous(false);
        appointment8.setSchedules(schedulesRepository.findById(2).orElse(null));
        appointment8.setStaffs(staff2);
        appointment8.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment8);

        Appointments appointment9 = new Appointments();
        appointment9.setDoctors(doctors);
        appointment9.setMedicalRecords(medicalRecords);
        appointment9.setCustomers(customers);
        appointment9.setStatus(false);
        LocalDateTime start9 = LocalDateTime.of(LocalDate.of(2025, 8, 3), fixedStartTime);
        appointment9.setStartTime(start9);
        appointment9.setEndTime(start9.plus(duration));
        appointment9.setAnonymous(true);
        appointment9.setSchedules(schedulesRepository.findById(3).orElse(null));
        appointment9.setStaffs(staff);
        appointment9.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment9);

        Appointments appointment10 = new Appointments();
        appointment10.setDoctors(doctors);
        appointment10.setMedicalRecords(medicalRecords);
        appointment10.setCustomers(customers);
        appointment10.setStatus(false);
        LocalDateTime start10 = LocalDateTime.of(LocalDate.of(2025, 8, 4), fixedStartTime);
        appointment10.setStartTime(start10);
        appointment10.setEndTime(start10.plus(duration));
        appointment10.setAnonymous(true);
        appointment10.setSchedules(schedulesRepository.findById(4).orElse(null));
        appointment10.setStaffs(staff);
        appointment10.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment10);

        Appointments appointment11 = new Appointments();
        appointment11.setDoctors(doctors);
        appointment11.setMedicalRecords(medicalRecords);
        appointment11.setCustomers(customers);
        appointment11.setStatus(false);
        LocalDateTime start11 = LocalDateTime.of(LocalDate.of(2025, 8, 5), fixedStartTime);
        appointment11.setStartTime(start11);
        appointment11.setEndTime(start11.plus(duration));
        appointment11.setAnonymous(false);
        appointment11.setSchedules(schedulesRepository.findById(5).orElse(null));
        appointment11.setStaffs(staff);
        appointment11.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment11);

        Appointments appointment12 = new Appointments();
        appointment12.setDoctors(doctors);
        appointment12.setMedicalRecords(medicalRecords);
        appointment12.setCustomers(customers);
        appointment12.setStatus(false);
        LocalDateTime start12 = LocalDateTime.of(LocalDate.of(2025, 8, 6), fixedStartTime);
        appointment12.setStartTime(start12);
        appointment12.setEndTime(start12.plus(duration));
        appointment12.setAnonymous(false);
        appointment12.setSchedules(schedulesRepository.findById(6).orElse(null));
        appointment12.setStaffs(staff);
        appointment12.setAppointmentType("Consultation");
        appointmentRepository.save(appointment12);

        Appointments appointment13 = new Appointments();
        appointment13.setDoctors(doctors);
        appointment13.setMedicalRecords(medicalRecords);
        appointment13.setCustomers(customers);
        appointment13.setStatus(false);
        LocalDateTime start13 = LocalDateTime.of(LocalDate.of(2025, 8, 7), fixedStartTime);
        appointment13.setStartTime(start13);
        appointment13.setEndTime(start13.plus(duration));
        appointment13.setAnonymous(true);
        appointment13.setSchedules(schedulesRepository.findById(7).orElse(null));
        appointment13.setStaffs(staff);
        appointment13.setAppointmentType("Consultation");
        appointmentRepository.save(appointment13);

        Appointments appointment14 = new Appointments();
        appointment14.setDoctors(doctors);
        appointment14.setMedicalRecords(medicalRecords);
        appointment14.setCustomers(customers);
        appointment14.setStatus(false);
        LocalDateTime start14 = LocalDateTime.of(LocalDate.of(2025, 8, 8), fixedStartTime);
        appointment14.setStartTime(start14);
        appointment14.setEndTime(start14.plus(duration));
        appointment14.setAnonymous(false);
        appointment14.setSchedules(schedulesRepository.findById(8).orElse(null));
        appointment14.setStaffs(staff);
        appointment14.setAppointmentType("Consultation");
        appointmentRepository.save(appointment14);

        Appointments appointment15 = new Appointments();
        appointment15.setDoctors(doctors);
        appointment15.setMedicalRecords(medicalRecords);
        appointment15.setCustomers(customers);
        appointment15.setStatus(false);
        LocalDateTime start15 = LocalDateTime.of(LocalDate.of(2025, 8, 9), fixedStartTime);
        appointment15.setStartTime(start15);
        appointment15.setEndTime(start15.plus(duration));
        appointment15.setAnonymous(false);
        appointment15.setSchedules(schedulesRepository.findById(9).orElse(null));
        appointment15.setStaffs(staff);
        appointment15.setAppointmentType("Consultation");
        appointmentRepository.save(appointment15);

        Appointments appointment16 = new Appointments();
        appointment16.setDoctors(doctors);
        appointment16.setMedicalRecords(medicalRecords);
        appointment16.setCustomers(customers);
        appointment16.setStatus(false);
        LocalDateTime start16 = LocalDateTime.of(LocalDate.of(2025, 8, 10), fixedStartTime);
        appointment16.setStartTime(start16);
        appointment16.setEndTime(start16.plus(duration));
        appointment16.setAnonymous(true);
        appointment16.setSchedules(schedulesRepository.findById(10).orElse(null));
        appointment16.setStaffs(staff);
        appointment16.setAppointmentType("Consultation");
        appointmentRepository.save(appointment16);

        Appointments appointment17 = new Appointments();
        appointment17.setDoctors(doctors);
        appointment17.setMedicalRecords(medicalRecords);
        appointment17.setCustomers(customers);
        appointment17.setStatus(false);
        LocalDateTime start17 = LocalDateTime.of(LocalDate.of(2025, 8, 11), fixedStartTime);
        appointment17.setStartTime(start17);
        appointment17.setEndTime(start17.plus(duration));
        appointment17.setAnonymous(false);
        appointment17.setSchedules(schedulesRepository.findById(11).orElse(null));
        appointment17.setStaffs(staff);
        appointment17.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment17);

        Appointments appointment18 = new Appointments();
        appointment18.setDoctors(doctors);
        appointment18.setMedicalRecords(medicalRecords);
        appointment18.setCustomers(customers);
        appointment18.setStatus(false);
        LocalDateTime start18 = LocalDateTime.of(LocalDate.of(2025, 8, 12), fixedStartTime);
        appointment18.setStartTime(start18);
        appointment18.setEndTime(start18.plus(duration));
        appointment18.setAnonymous(false);
        appointment18.setSchedules(schedulesRepository.findById(12).orElse(null));
        appointment18.setStaffs(staff);
        appointment18.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment18);

        Appointments appointment19 = new Appointments();
        appointment19.setDoctors(doctors);
        appointment19.setMedicalRecords(medicalRecords);
        appointment19.setCustomers(customers);
        appointment19.setStatus(false);
        LocalDateTime start19 = LocalDateTime.of(LocalDate.of(2025, 8, 13), fixedStartTime);
        appointment19.setStartTime(start19);
        appointment19.setEndTime(start19.plus(duration));
        appointment19.setAnonymous(false);
        appointment19.setSchedules(schedulesRepository.findById(13).orElse(null));
        appointment19.setStaffs(staff);
        appointment19.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment19);

        Appointments appointment20 = new Appointments();
        appointment20.setDoctors(doctors);
        appointment20.setMedicalRecords(medicalRecords);
        appointment20.setCustomers(customers);
        appointment20.setStatus(false);
        LocalDateTime start20 = LocalDateTime.of(LocalDate.of(2025, 8, 14), fixedStartTime);
        appointment20.setStartTime(start20);
        appointment20.setEndTime(start20.plus(duration));
        appointment20.setAnonymous(false);
        appointment20.setSchedules(schedulesRepository.findById(14).orElse(null));
        appointment20.setStaffs(staff);
        appointment20.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment20);

        Appointments appointment21 = new Appointments();
        appointment21.setDoctors(doctors);
        appointment21.setMedicalRecords(medicalRecords);
        appointment21.setCustomers(customers);
        appointment21.setStatus(false);
        LocalDateTime start21 = LocalDateTime.of(LocalDate.of(2025, 8, 15), fixedStartTime);
        appointment21.setStartTime(start21);
        appointment21.setEndTime(start21.plus(duration));
        appointment21.setAnonymous(false);
        appointment21.setSchedules(schedulesRepository.findById(15).orElse(null));
        appointment21.setStaffs(staff);
        appointment21.setAppointmentType("Test HIV");
        appointmentRepository.save(appointment21);


        for (int i = 0; i < 10; i++) {
            Appointments appointment = new Appointments();
            appointment.setDoctors(doctors);
            appointment.setMedicalRecords(medicalRecords);
            appointment.setCustomers(customers);
            appointment.setStatus(true);

            // Ngày từ 1/9/2025 đến 10/9/2025
            LocalDate date = LocalDate.of(2025, 9, 1 + i);
            LocalDateTime start = LocalDateTime.of(date, fixedStartTime);
            appointment.setStartTime(start);
            appointment.setEndTime(start.plus(duration));

            appointment.setAnonymous(false);
            appointment.setSchedules(schedulesRepository.findById(26 + i).orElse(null));
            appointment.setStaffs(staff);
            appointment.setAppointmentType("Test HIV");

            appointmentRepository.save(appointment);
        }
        for (int i = 0; i < 10; i++) {
            Appointments appointment = new Appointments();
            appointment.setDoctors(doctors);
            appointment.setMedicalRecords(medicalRecords);
            appointment.setCustomers(customers);
            appointment.setStatus(true);

            // Ngày từ 1/9/2025 đến 10/9/2025
            LocalDate date = LocalDate.of(2025, 9, 11 + i);
            LocalDateTime start = LocalDateTime.of(date, fixedStartTime);
            appointment.setStartTime(start);
            appointment.setEndTime(start.plus(duration));

            appointment.setAnonymous(true);
            appointment.setSchedules(schedulesRepository.findById(36 + i).orElse(null));
            appointment.setStaffs(staff);
            appointment.setAppointmentType("Test HIV");

            appointmentRepository.save(appointment);
        }
        for (int i = 0; i < 10; i++) {
            Appointments appointment = new Appointments();
            appointment.setDoctors(doctors);
            appointment.setMedicalRecords(medicalRecords);
            appointment.setCustomers(customers);
            appointment.setStatus(true);

            // Ngày từ 1/9/2025 đến 10/9/2025
            LocalDate date = LocalDate.of(2025, 10, 1 + i);
            LocalDateTime start = LocalDateTime.of(date, fixedStartTime);
            appointment.setStartTime(start);
            appointment.setEndTime(start.plus(duration));

            appointment.setAnonymous(false);
            appointment.setSchedules(schedulesRepository.findById(51 + i).orElse(null));
            appointment.setStaffs(staff);
            appointment.setAppointmentType("Consultation");

            appointmentRepository.save(appointment);
        }
        for (int i = 0; i < 10; i++) {
            Appointments appointment = new Appointments();
            appointment.setDoctors(doctors);
            appointment.setMedicalRecords(medicalRecords);
            appointment.setCustomers(customers);
            appointment.setStatus(true);

            // Ngày từ 1/9/2025 đến 10/9/2025
            LocalDate date = LocalDate.of(2025, 10, 11 + i);
            LocalDateTime start = LocalDateTime.of(date, fixedStartTime);
            appointment.setStartTime(start);
            appointment.setEndTime(start.plus(duration));

            appointment.setAnonymous(true);
            appointment.setSchedules(schedulesRepository.findById(61 + i).orElse(null));
            appointment.setStaffs(staff);
            appointment.setAppointmentType("Consultation");

            appointmentRepository.save(appointment);
        }


        //Create TreatmentAdd commentMore actions
        TreatmentPlans treatmentPlans = new TreatmentPlans();
        treatmentPlans.setDoctors(doctors);
        treatmentPlans.setArvReqimentID(r1);
        treatmentPlans.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans.setDosageTime(LocalTime.of(8, 0));
        treatmentPlans.setAppointments(appointment1);
        treatmentPlans.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans);

        TreatmentPlans treatmentPlans1 = new TreatmentPlans();
        treatmentPlans1.setDoctors(doctors);
        treatmentPlans1.setArvReqimentID(r2);
        treatmentPlans1.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans1.setDosageTime(LocalTime.of(10, 0));
        treatmentPlans1.setAppointments(appointment2);
        treatmentPlans1.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans1);

        TreatmentPlans treatmentPlans2 = new TreatmentPlans();
        treatmentPlans2.setDoctors(doctors);
        treatmentPlans2.setArvReqimentID(r3);
        treatmentPlans2.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans2.setDosageTime(LocalTime.of(12, 0));
        treatmentPlans2.setAppointments(appointment3);
        treatmentPlans2.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans2);

        TreatmentPlans treatmentPlans3 = new TreatmentPlans();
        treatmentPlans3.setDoctors(doctors);
        treatmentPlans3.setArvReqimentID(r1);
        treatmentPlans3.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans3.setDosageTime(LocalTime.of(14, 0));
        treatmentPlans3.setAppointments(appointment4);
        treatmentPlans3.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans3);

        TreatmentPlans treatmentPlans4 = new TreatmentPlans();
        treatmentPlans4.setDoctors(doctors);
        treatmentPlans4.setArvReqimentID(r1);
        treatmentPlans4.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans4.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans4.setAppointments(appointment5);
        treatmentPlans4.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans4);

        TreatmentPlans treatmentPlans5 = new TreatmentPlans();
        treatmentPlans5.setDoctors(doctors);
        treatmentPlans5.setArvReqimentID(r1);
        treatmentPlans5.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans5.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans5.setAppointments(appointment11);
        treatmentPlans5.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans5);

        TreatmentPlans treatmentPlans6 = new TreatmentPlans();
        treatmentPlans6.setDoctors(doctors);
        treatmentPlans6.setArvReqimentID(r1);
        treatmentPlans6.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans6.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans6.setAppointments(appointment12);
        treatmentPlans6.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans6);

        TreatmentPlans treatmentPlans7 = new TreatmentPlans();
        treatmentPlans7.setDoctors(doctors);
        treatmentPlans7.setArvReqimentID(r1);
        treatmentPlans7.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans7.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans7.setAppointments(appointment13);
        treatmentPlans7.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans7);

        TreatmentPlans treatmentPlans8 = new TreatmentPlans();
        treatmentPlans8.setDoctors(doctors);
        treatmentPlans8.setArvReqimentID(r1);
        treatmentPlans8.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans8.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans8.setAppointments(appointment14);
        treatmentPlans8.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans8);

        TreatmentPlans treatmentPlans9 = new TreatmentPlans();
        treatmentPlans9.setDoctors(doctors);
        treatmentPlans9.setArvReqimentID(r1);
        treatmentPlans9.setPlanDescription("Kế hoạch điều trị HIV cho người lớn");
        treatmentPlans9.setDosageTime(LocalTime.of(16, 0));
        treatmentPlans9.setAppointments(appointment15);
        treatmentPlans9.setStatus(false);
        treatmentPlansRepository.save(treatmentPlans9);


        //Create Test Result
        TestResults testResults = new TestResults();
        testResults.setDoctors(doctors);
        testResults.setCustomers(customers);
        testResults.setAppointments(appointment1);
        testResults.setTreatmentPlan(treatmentPlans);
        testResults.setResultValue(false);
        testResults.setNotes("Notes");
        testResults.setRe_examination(true);
        testResults.setHivViralLoad(69);
        testResults.setCD4(96);
        testResults.setTestType("HIV");
        testResults.setTestDate(appointment1.getStartTime().toLocalDate());
        testResultRepository.save(testResults);

        TestResults testResults1 = new TestResults();
        testResults1.setDoctors(doctors);
        testResults1.setCustomers(customers);
        testResults1.setAppointments(appointment2);
        testResults1.setTreatmentPlan(treatmentPlans1);
        testResults1.setResultValue(true);
        testResults1.setNotes("Notes");
        testResults1.setRe_examination(false);
        testResults1.setHivViralLoad(69);
        testResults1.setCD4(96);
        testResults1.setTestType("HIV");
        testResults1.setTestDate(appointment2.getStartTime().toLocalDate());
        testResultRepository.save(testResults1);

        TestResults testResults2 = new TestResults();
        testResults2.setDoctors(doctors);
        testResults2.setCustomers(customers);
        testResults2.setAppointments(appointment3);
        testResults2.setTreatmentPlan(treatmentPlans2);
        testResults2.setResultValue(true);
        testResults2.setNotes("Notes");
        testResults2.setRe_examination(true);
        testResults2.setHivViralLoad(69);
        testResults2.setCD4(96);
        testResults2.setTestType("HIV");
        testResults2.setTestDate(appointment3.getStartTime().toLocalDate());
        testResultRepository.save(testResults2);

        TestResults testResults3 = new TestResults();
        testResults3.setDoctors(doctors);
        testResults3.setCustomers(customers);
        testResults3.setAppointments(appointment4);
        testResults3.setTreatmentPlan(treatmentPlans3);
        testResults3.setResultValue(false);
        testResults3.setNotes("Notes");
        testResults3.setRe_examination(false);
        testResults3.setHivViralLoad(69);
        testResults3.setCD4(96);
        testResults3.setTestType("HIV");
        testResults3.setTestDate(appointment4.getStartTime().toLocalDate());
        testResultRepository.save(testResults3);

        TestResults testResults4 = new TestResults();
        testResults4.setDoctors(doctors);
        testResults4.setCustomers(customers);
        testResults4.setAppointments(appointment5);
        testResults4.setTreatmentPlan(treatmentPlans4);
        testResults4.setResultValue(true);
        testResults4.setNotes("Notes");
        testResults4.setRe_examination(true);
        testResults4.setHivViralLoad(69);
        testResults4.setCD4(96);
        testResults4.setTestType("HIV");
        testResults4.setTestDate(appointment5.getStartTime().toLocalDate());
        testResultRepository.save(testResults4);

        TestResults testResults5 = new TestResults();
        testResults5.setDoctors(doctors);
        testResults5.setCustomers(customers);
        testResults5.setAppointments(appointment6);
        testResults5.setTreatmentPlan(treatmentPlans5);
        testResults5.setResultValue(true);
        testResults5.setNotes("Notes");
        testResults5.setRe_examination(true);
        testResults5.setHivViralLoad(69);
        testResults5.setCD4(96);
        testResults5.setTestType("HIV");
        testResults5.setTestDate(appointment6.getStartTime().toLocalDate());
        testResultRepository.save(testResults5);

        TestResults testResults6 = new TestResults();
        testResults6.setDoctors(doctors);
        testResults6.setCustomers(customers);
        testResults6.setAppointments(appointment7);
        testResults6.setTreatmentPlan(treatmentPlans6);
        testResults6.setResultValue(true);
        testResults6.setNotes("Notes");
        testResults6.setRe_examination(true);
        testResults6.setHivViralLoad(69);
        testResults6.setCD4(96);
        testResults6.setTestType("HIV");
        testResults6.setTestDate(appointment7.getStartTime().toLocalDate());
        testResultRepository.save(testResults6);

        TestResults testResults7 = new TestResults();
        testResults7.setDoctors(doctors);
        testResults7.setCustomers(customers);
        testResults7.setAppointments(appointment8);
        testResults7.setTreatmentPlan(treatmentPlans7);
        testResults7.setResultValue(true);
        testResults7.setNotes("Notes");
        testResults7.setRe_examination(true);
        testResults7.setHivViralLoad(69);
        testResults7.setCD4(96);
        testResults7.setTestType("HIV");
        testResults7.setTestDate(appointment8.getStartTime().toLocalDate());
        testResultRepository.save(testResults7);

        TestResults testResults8 = new TestResults();
        testResults8.setDoctors(doctors);
        testResults8.setCustomers(customers);
        testResults8.setAppointments(appointment9);
        testResults8.setTreatmentPlan(treatmentPlans8);
        testResults8.setResultValue(true);
        testResults8.setNotes("Notes");
        testResults8.setRe_examination(true);
        testResults8.setHivViralLoad(69);
        testResults8.setCD4(96);
        testResults8.setTestType("HIV");
        testResults8.setTestDate(appointment9.getStartTime().toLocalDate());
        testResultRepository.save(testResults8);

        TestResults testResults9 = new TestResults();
        testResults9.setDoctors(doctors);
        testResults9.setCustomers(customers);
        testResults9.setAppointments(appointment10);
        testResults9.setTreatmentPlan(treatmentPlans9);
        testResults9.setResultValue(true);
        testResults9.setNotes("Notes");
        testResults9.setRe_examination(true);
        testResults9.setHivViralLoad(69);
        testResults9.setCD4(96);
        testResults9.setTestType("HIV");
        testResults9.setTestDate(appointment10.getStartTime().toLocalDate());
        testResultRepository.save(testResults9);


        //Create Reminder Dosage
        Reminders remindersDosage = new Reminders();
        remindersDosage.setCustomers(customers);
        remindersDosage.setReminderTime(treatmentPlans.getDosageTime().atDate(LocalDate.now()));
        remindersDosage.setMessage("uống thuốc");
        remindersDosage.setTestResults(testResults);
        remindersDosage.setAppointments(appointment1);
        remindersDosage.setStaffs(staff);
        remindersDosage.setReminderType("Dosage Reminder");
        remindersDosage.setStatus(false);
        reminderRepository.save(remindersDosage);

        Reminders remindersDosage1 = new Reminders();
        remindersDosage1.setCustomers(customers);
        remindersDosage1.setReminderTime(treatmentPlans1.getDosageTime().atDate(LocalDate.now()));
        remindersDosage1.setMessage("uống thuốc");
        remindersDosage1.setTestResults(testResults1);
        remindersDosage1.setAppointments(appointment2);
        remindersDosage1.setStaffs(staff);
        remindersDosage1.setReminderType("Dosage Reminder");
        remindersDosage1.setStatus(false);
        reminderRepository.save(remindersDosage1);

        Reminders remindersDosage2 = new Reminders();
        remindersDosage2.setCustomers(customers);
        remindersDosage2.setReminderTime(treatmentPlans2.getDosageTime().atDate(LocalDate.now()));
        remindersDosage2.setMessage("uống thuốc");
        remindersDosage2.setTestResults(testResults2);
        remindersDosage2.setAppointments(appointment3);
        remindersDosage2.setStaffs(staff);
        remindersDosage2.setReminderType("Dosage Reminder");
        remindersDosage2.setStatus(false);
        reminderRepository.save(remindersDosage2);

        Reminders remindersDosage3 = new Reminders();
        remindersDosage3.setCustomers(customers);
        remindersDosage3.setReminderTime(treatmentPlans3.getDosageTime().atDate(LocalDate.now()));
        remindersDosage3.setMessage("uống thuốc");
        remindersDosage3.setTestResults(testResults3);
        remindersDosage3.setAppointments(appointment4);
        remindersDosage3.setStaffs(staff);
        remindersDosage3.setReminderType("Dosage Reminder");
        remindersDosage3.setStatus(false);
        reminderRepository.save(remindersDosage3);

        Reminders remindersDosage4 = new Reminders();
        remindersDosage4.setCustomers(customers);
        remindersDosage4.setReminderTime(treatmentPlans4.getDosageTime().atDate(LocalDate.now()));
        remindersDosage4.setMessage("uống thuốc");
        remindersDosage4.setTestResults(testResults4);
        remindersDosage4.setAppointments(appointment5);
        remindersDosage4.setStaffs(staff);
        remindersDosage4.setReminderType("Dosage Reminder");
        remindersDosage4.setStatus(true);
        reminderRepository.save(remindersDosage4);

        Reminders remindersReExam5 = new Reminders();
        remindersReExam5.setCustomers(customers);
        remindersReExam5.setReminderTime(appointment6.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam5.setMessage("tái khám");
        remindersReExam5.setTestResults(testResults5);
        remindersReExam5.setAppointments(appointment6);
        remindersReExam5.setStaffs(staff);
        remindersReExam5.setReminderType("Re-Exam Reminder");
        remindersReExam5.setStatus(false);
        reminderRepository.save(remindersReExam5);

        Reminders remindersReExam6 = new Reminders();
        remindersReExam6.setCustomers(customers);
        remindersReExam6.setReminderTime(appointment7.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam6.setMessage("tái khám");
        remindersReExam6.setTestResults(testResults6);
        remindersReExam6.setAppointments(appointment7);
        remindersReExam6.setStaffs(staff);
        remindersReExam6.setReminderType("Re-Exam Reminder");
        remindersReExam6.setStatus(false);
        reminderRepository.save(remindersReExam6);

        Reminders remindersReExam7 = new Reminders();
        remindersReExam7.setCustomers(customers);
        remindersReExam7.setReminderTime(appointment8.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam7.setMessage("tái khám");
        remindersReExam7.setTestResults(testResults7);
        remindersReExam7.setAppointments(appointment8);
        remindersReExam7.setStaffs(staff);
        remindersReExam7.setReminderType("Re-Exam Reminder");
        remindersReExam7.setStatus(false);
        reminderRepository.save(remindersReExam7);

        Reminders remindersReExam8 = new Reminders();
        remindersReExam8.setCustomers(customers);
        remindersReExam8.setReminderTime(appointment9.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam8.setMessage("tái khám");
        remindersReExam8.setTestResults(testResults8);
        remindersReExam8.setAppointments(appointment9);
        remindersReExam8.setStaffs(staff);
        remindersReExam8.setReminderType("Re-Exam Reminder");
        remindersReExam8.setStatus(false);
        reminderRepository.save(remindersReExam8);

        Reminders remindersReExam9 = new Reminders();
        remindersReExam9.setCustomers(customers);
        remindersReExam9.setReminderTime(appointment10.getStartTime().toLocalDate().atTime(8, 0));
        remindersReExam9.setMessage("tái khám");
        remindersReExam9.setTestResults(testResults9);
        remindersReExam9.setAppointments(appointment10);
        remindersReExam9.setStaffs(staff);
        remindersReExam9.setReminderType("Re-Exam Reminder");
        remindersReExam9.setStatus(true);
        reminderRepository.save(remindersReExam9);


        // Example: Add in your createModel() after appointments, doctors, customers are created and saved

        Consultations consultation1 = new Consultations();
        consultation1.setAppointments(appointment12);
        consultation1.setDoctors(doctors);
        consultation1.setCustomers(customers);
        consultation1.setConsultationDate(appointment12.getStartTime().toLocalDate());
        consultation1.setNotes("Initial consultation and HIV test.");
        consultationRepository.save(consultation1);

        Consultations consultation2 = new Consultations();
        consultation2.setAppointments(appointment13);
        consultation2.setDoctors(doctors);
        consultation2.setCustomers(customers);
        consultation2.setConsultationDate(appointment13.getStartTime().toLocalDate());
        consultation2.setNotes("Follow-up consultation, discussed test results.");
        consultationRepository.save(consultation2);

        Consultations consultation3 = new Consultations();
        consultation3.setAppointments(appointment14);
        consultation3.setDoctors(doctors);
        consultation3.setCustomers(customers);
        consultation3.setConsultationDate(appointment14.getStartTime().toLocalDate());
        consultation3.setNotes("Consultation for treatment plan.");
        consultationRepository.save(consultation3);

        Consultations consultation4 = new Consultations();
        consultation4.setAppointments(appointment15);
        consultation4.setDoctors(doctors);
        consultation4.setCustomers(customers);
        consultation4.setConsultationDate(appointment15.getStartTime().toLocalDate());
        consultation4.setNotes("Routine check-up and medication review.");
        consultationRepository.save(consultation4);

        Consultations consultation5 = new Consultations();
        consultation5.setAppointments(appointment16);
        consultation5.setDoctors(doctors);
        consultation5.setCustomers(customers);
        consultation5.setConsultationDate(appointment16.getStartTime().toLocalDate());
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