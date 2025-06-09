package swp391_gr7.hivsystem.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.Reminder;
import swp391_gr7.hivsystem.model.Staff;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.ReminderRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceImp implements ReminderService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    public static String error = "";

    @Override
    public Reminder addReminder(ReminderCreateRequest request) {
        // Khởi tạo chuỗi lỗi rỗng
        error = "";
        // xử lý customer
        Optional<Customer> customerOpt = customerRepository.findCustomersByMail(request.getCustomerMail());
        Customer customer = null;
        if(customerOpt.isEmpty()) {
            error += "Customer not found with mail";
        } else {
            customer = customerOpt.get();
        }

        // xử lý staff
        Optional<Staff> staffOpt = staffRepository.findStaffByMail(request.getStaffMail());
        Staff staff = null;
        if(staffOpt.isEmpty()) {
            error += ", Staff not found with mail";
        } else {
            staff = staffOpt.get();
        }
        if(!error.isEmpty()) {
            // Nếu có lỗi, in ra và trả null
            System.out.println(error);
            return null;
        }
        // Tạo mới Reminder nếu không có lỗi
        Reminder reminder = new Reminder();
        reminder.setCustomer(customer);
        reminder.setStaff(staff);
        reminder.setReminderType(request.getReminderType());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setMessage(request.getMessage());
        reminder.setStatus(false);

        return reminderRepository.save(reminder);
    }

    @Override
    public List<Reminder> getAllReminders() {
        List<Reminder> reminderList = reminderRepository.findAll();
        return reminderList;
    }

    @Override
    @Scheduled(fixedRate = 60000) // Chạy mỗi 1 phút
    public void sendDueReminder() {
        // Tìm reminder có status false và đến thời điểm nhắc nhở
        List<Reminder> reminderDue = reminderRepository.findReminderStatusFalseAndReminderTimeBefore(LocalDateTime.now());

        // Chạy vòng lặp để tìm reminder theo điều kiện
        for(Reminder reminder : reminderDue) {
            try{
                // tạo mimemesage
                MimeMessage message = mailSender.createMimeMessage();
                // lưu message vào helper
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                // Gửi đến người
                helper.setTo(reminder.getCustomer().getUser().getEmail());
                // Tiêu đề
                helper.setSubject(reminder.getReminderType());
                // Nội dung
                helper.setText(reminder.getMessage());
                // Người gửi
                helper.setFrom(reminder.getStaff().getUser().getEmail());

                // gửi đi
                mailSender.send(message);
                // gửi thành công, tự động đổi status thành true
                reminder.setStatus(true);
                // rồi lưu vào database
                reminderRepository.save(reminder);
            }catch (Exception e) {
                e.printStackTrace(); // in ra log lỗi
            }
        }
    }
}
