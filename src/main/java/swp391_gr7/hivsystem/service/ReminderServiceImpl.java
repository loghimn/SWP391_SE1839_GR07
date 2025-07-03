package swp391_gr7.hivsystem.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ReminderDosageCreateRequest;
import swp391_gr7.hivsystem.dto.request.ReminderReExamCreateRequest;
import swp391_gr7.hivsystem.dto.request.ReminderUpdateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository remindersRepository;
    @Autowired
    private CustomerRepository customersRepository;
    @Autowired
    private StaffRepository staffsRepository;
    @Autowired
    private TestResultRepository testResultsRepository;
    @Autowired
    private AppointmentRepository appointmentsRepository;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public Reminders createReminderDosage(int id, ReminderDosageCreateRequest request) {
        Reminders reminder = new Reminders();
        TestResults testResult = testResultsRepository.findById(request.getTestResultId()).orElse(null);
        if (testResult == null || testResult.getTreatmentPlan() == null) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }
        reminder.setCustomers(customersRepository.findById(request.getCustomerId()).orElse(null));
        if (reminder.getCustomers() == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        Staffs staffs = staffsRepository.findById(id).orElse(null);
        if (staffs == null) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }
        Appointments appointments = appointmentsRepository.findById(testResult.getAppointments().getAppointmentId() + 1).orElse(null);
        if (appointments == null) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }
        reminder.setReminderType("Dosage Reminder");
        reminder.setMessage(request.getMessage());
        reminder.setStatus(true);
        reminder.setStaffs(staffs);
        reminder.setTestResults(testResult);
        reminder.setAppointments(appointments);
        // Set reminderTime based on dosageTime in TreatmentPlans
        if (testResult != null && testResult.getTreatmentPlan() != null && testResult.getTreatmentPlan().getDosageTime() != null) {
            LocalDateTime reminderDateTime = LocalDateTime.of(testResult.getTestDate(), testResult.getTreatmentPlan().getDosageTime());
            reminder.setReminderTime(reminderDateTime);
        } else {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_HAVE_DOSAGE_TIME);
        }
        // Set staff if needed (if you have logic to get staff from context)
        return remindersRepository.save(reminder);
    }

    @Override
    public Reminders createReminderReExam(int id, ReminderReExamCreateRequest request) {
        Reminders reminder = new Reminders();
        Appointments appointments = appointmentsRepository.findById(request.getAppointmentId()).orElse(null);
        if (appointments == null) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        } else if (!appointments.isStatus()) {
            throw new AppException(ErrorCode.APPOINTMENT_ALREADY_IS_NOT_ACTIVE);
        }
        Customers customer = customersRepository.findById(appointments.getCustomers().getCustomerId()).orElse(null);
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        reminder.setCustomers(customer);
        List<TestResults> testResultsList = testResultsRepository.findByCustomers_CustomerId(customer.getCustomerId());
        if (testResultsList.isEmpty()) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }

        TestResults testResult = null;
        for (int i = testResultsList.size() - 1; i >= 0; i--) {
            if (testResultsList.get(i).isRe_examination()) {
                testResult = testResultsList.get(i);
                break;
            }
        }
        if (testResult == null) {
            throw new AppException(ErrorCode.TEST_RESULT_NOT_FOUND);
        }

        reminder.setReminderType("Re-Exam Reminder");
        reminder.setMessage(request.getMessage());
        reminder.setStatus(true);
        reminder.setStaffs(staffsRepository.findById(id).orElse(null));
        reminder.setTestResults(testResult);
        reminder.setAppointments(appointmentsRepository.findById(request.getAppointmentId()).orElse(null));
        // Set reminderTime based on dosageTime in TreatmentPlans
        if (appointments != null && appointments.getAppointmentTime() != null) {
            LocalDate appointmentDay = appointments.getAppointmentTime();
            LocalDate reminderDay = appointmentDay.minusDays(1);
            LocalTime reminderTime = LocalTime.of(8, 0);
            LocalDateTime reminderDateTime = LocalDateTime.of(reminderDay, reminderTime);
            reminder.setReminderTime(reminderDateTime); // store full datetime
        } else if (appointments == null) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        } else {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_HAVE_TIME);
        }
        // Set staff if needed (if you have logic to get staff from context)
        return remindersRepository.save(reminder);
    }

    @Override
    public Reminders updateReminderDosage(int id, ReminderUpdateRequest request) {
        return remindersRepository.findById(id).map(existing -> {
            existing.setMessage(request.getMessage());
            return remindersRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public Reminders updateReminderReExam(int id, ReminderUpdateRequest request) {
        return remindersRepository.findById(id).map(existing -> {
            existing.setMessage(request.getMessage());
            return remindersRepository.save(existing);
        }).orElse(null);
    }


    @Override
    @Scheduled(fixedRate = 60000)
    public void sendDueReminderReExam() {
        List<Reminders> remindersDue = remindersRepository.findReminderStatusFalseAndReminderTimeBefore(LocalDateTime.now());
        for (Reminders reminders : remindersDue) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(reminders.getCustomers().getUsers().getEmail());
                helper.setSubject(reminders.getReminderType());
                helper.setText(reminders.getMessage());
                helper.setFrom(reminders.getStaffs().getUsers().getEmail());
                mailSender.send(message);
                reminders.setStatus(false);
                remindersRepository.save(reminders);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Reminders getReminderById(int id) {
        return remindersRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.REMINDER_NOT_FOUND));
    }

    @Override
    public List<Reminders> getAllReminders() {
        if (remindersRepository.count() == 0) {
            throw new AppException(ErrorCode.REMINDER_NOT_FOUND);
        }
        return remindersRepository.findAll();
    }

    @Override
    public List<Reminders> getAllRemindersActive() {
        if (remindersRepository.count() == 0) {
            throw new AppException(ErrorCode.REMINDER_NOT_FOUND);
        }
        List<Reminders> activeReminders = remindersRepository.findAllByStatus(true);
        return activeReminders;
    }


    @Override
    public void deleteReminder(int id) {
        if (!remindersRepository.existsById(id)) {
            throw new AppException(ErrorCode.REMINDER_NOT_FOUND);
        }
        remindersRepository.findById(id).ifPresent(reminder -> {
            if (!reminder.isStatus()) {
                throw new AppException(ErrorCode.REMINDER_ALREADY_DELETED);
            }
            reminder.setStatus(false);
            remindersRepository.save(reminder);
        });
    }

    @Override
    public Reminders getMyReminderByIdCus(int id) {
        if (!remindersRepository.existsById(id)) {
            throw new AppException(ErrorCode.REMINDER_NOT_FOUND);
        }
        return remindersRepository.findRemindersByCustomersCustomerId(id);
    }

    @Override
    public List<Reminders> getMyReminderByIdStaff(int id) {
        if (!remindersRepository.existsById(id)) {
            throw new AppException(ErrorCode.REMINDER_NOT_FOUND);
        }
        return remindersRepository.findRemindersByStaffsStaffId(id);
    }
}