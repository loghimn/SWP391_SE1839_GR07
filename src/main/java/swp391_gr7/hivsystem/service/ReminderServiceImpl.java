package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;

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

    @Override
    public Reminders createReminder(ReminderCreateRequest request) {
        Reminders reminder = new Reminders();
        TestResults testResult = testResultsRepository.findById(request.getTestResultId())
                .orElseThrow(() -> new AppException(ErrorCode.REMINDER_NOT_FOUND_TEST_RESULT));

        reminder.setCustomers(customersRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.REMINDER_NOT_FOUND_CUSTOMER)));

        reminder.setReminderType(request.getReminderType());
        reminder.setMessage(request.getMessage());
        reminder.setStatus(request.getStatus());
        reminder.setStaffs(staffsRepository.findById(request.getStaffId())
                        .orElseThrow(() -> new AppException(ErrorCode.REMINDER_NOT_FOUND_STAFF)));
        reminder.setTestResults(testResult);
        reminder.setAppointments(appointmentsRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new AppException(ErrorCode.REMINDER_NOT_FOUND_APPOINTMENT)));
        // Set reminderTime based on dosageTime in TreatmentPlans
        if (testResult != null && testResult.getTreatmentPlan() != null && testResult.getTreatmentPlan().getDosageTime() != null) {
            reminder.setReminderTime(testResult.getTreatmentPlan().getDosageTime());
        } else {
            // fallback: set to midnight
            reminder.setReminderTime(java.time.LocalTime.MIDNIGHT);
        }
        // Set staff if needed (if you have logic to get staff from context)
        return remindersRepository.save(reminder);
    }

    @Override
    public Reminders updateReminder(int id, ReminderCreateRequest request) {
        return remindersRepository.findById(id).map(existing -> {
            TestResults testResult = testResultsRepository.findById(request.getTestResultId()).orElse(null);
            existing.setCustomers(customersRepository.findById(request.getCustomerId()).orElse(null));
            existing.setReminderType(request.getReminderType());
            existing.setMessage(request.getMessage());
            existing.setStatus(request.getStatus());
            existing.setStaffs(staffsRepository.findById(request.getStaffId()).orElse(null));
            existing.setTestResults(testResult);
            existing.setAppointments(appointmentsRepository.findById(request.getAppointmentId()).orElse(null));
            // Set reminderTime based on dosageTime in TreatmentPlans
            if (testResult != null && testResult.getTreatmentPlan() != null && testResult.getTreatmentPlan().getDosageTime() != null) {
                existing.setReminderTime(testResult.getTreatmentPlan().getDosageTime());
            } else {
                existing.setReminderTime(java.time.LocalTime.MIDNIGHT);
            }
            // Set staff if needed (if you have logic to get staff from context)
            return remindersRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public Reminders getReminderById(int id) {
        return remindersRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reminders> getAllReminders() {
        return remindersRepository.findAll();
    }

    @Override
    public void deleteReminder(int id) {
        remindersRepository.deleteById(id);
    }
}