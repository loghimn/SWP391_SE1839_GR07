package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ReminderDosageCreateRequest;
import swp391_gr7.hivsystem.dto.request.ReminderReExamCreateRequest;
import swp391_gr7.hivsystem.dto.request.ReminderUpdateRequest;
import swp391_gr7.hivsystem.model.Reminders;

import java.util.List;

public interface ReminderService {
    Reminders createReminderDosage(int id, ReminderDosageCreateRequest request);

    Reminders createReminderReExam(int id, ReminderReExamCreateRequest request);

    Reminders getReminderById(int id);

    List<Reminders> getAllReminders();

    List<Reminders> getAllRemindersActive();

    Reminders updateReminderDosage(int id, ReminderUpdateRequest request);

    Reminders updateReminderReExam(int id, ReminderUpdateRequest request);

    void deleteReminder(int id);

    void sendDueReminderReExam();

    Reminders getMyReminderByIdCus(int id);

    List<Reminders> getMyReminderByIdStaff(int id);
}