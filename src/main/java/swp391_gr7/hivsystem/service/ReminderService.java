package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Reminders;

import java.util.List;

public interface ReminderService {
    Reminders createReminderDosage(int id, ReminderCreateRequest request);

    Reminders createReminderReExam(int id, ReminderCreateRequest request);

    Reminders getReminderById(int id);

    List<Reminders> getAllReminders();

    Reminders updateReminderDosage(int id, ReminderCreateRequest request);

    Reminders updateReminderReExam(int id, ReminderCreateRequest request);

    void deleteReminder(int id);

    void sendDueReminderReExam();

    Reminders getMyReminderById(int id);
}