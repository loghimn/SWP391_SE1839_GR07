package swp391_gr7.hivsystem.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.ReminderRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.TestResultRepository;

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
    @Autowired
    private TestResultRepository testResultRepository;
    public static String error = "";

    @Override
    public Reminders addReminder(ReminderCreateRequest request) {
        // Khởi tạo chuỗi lỗi rỗng
        error = "";
        // xử lý customer
        Optional<Customers> customerOpt = customerRepository.findCustomersByMail(request.getCustomerMail());
        Customers customers = null;
        if(customerOpt.isEmpty()) {
            error += "Customer not found with mail";
        } else {
            customers = customerOpt.get();
        }

        // xử lý staff
        Optional<Staffs> staffOpt = staffRepository.findStaffByMail(request.getStaffMail());
        Staffs staffs = null;
        if(staffOpt.isEmpty()) {
            error += ", Staff not found with mail";
        } else {
            staffs = staffOpt.get();
        }
        if(!error.isEmpty()) {
            // Nếu có lỗi, in ra và trả null
            System.out.println(error);
            return null;
        }

        // Tạo mới Reminder nếu không có lỗi
        Reminders reminders = new Reminders();
        reminders.setCustomers(customers);
        reminders.setStaffs(staffs);
        reminders.setReminderType(request.getReminderType());
        reminders.setReminderTime(request.getReminderTime());
        reminders.setMessage(request.getMessage());
        reminders.setStatus(false);

        return reminderRepository.save(reminders);
    }

    @Override
    public List<Reminders> getAllReminders() {
        List<Reminders> remindersList = reminderRepository.findAll();
        return remindersList;
    }

    @Override
    @Scheduled(fixedRate = 60000) // Chạy mỗi 1 phút
    public void sendDueReminder() {
        // Tìm reminder có status false và đến thời điểm nhắc nhở
        List<Reminders> remindersDue = reminderRepository.findReminderStatusFalseAndReminderTimeBefore(LocalDateTime.now());

        // Chạy vòng lặp để tìm reminder theo điều kiện
        for(Reminders reminders : remindersDue) {
            try{
                // tạo mimemesage
                MimeMessage message = mailSender.createMimeMessage();
                // lưu message vào helper
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                // Gửi đến người
                helper.setTo(reminders.getCustomers().getUsers().getEmail());
                // Tiêu đề
                helper.setSubject(reminders.getReminderType());
                // Nội dung
                helper.setText(reminders.getMessage());
                // Người gửi
                helper.setFrom(reminders.getStaffs().getUsers().getEmail());

                // gửi đi
                mailSender.send(message);
                // gửi thành công, tự động đổi status thành true
                reminders.setStatus(true);
                // rồi lưu vào database
                reminderRepository.save(reminders);
            }catch (Exception e) {
                e.printStackTrace(); // in ra log lỗi
            }
        }
    }
}
