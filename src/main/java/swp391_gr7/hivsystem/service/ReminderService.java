package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.dto.request.ReminderUpdateRequest;
import swp391_gr7.hivsystem.model.Reminders;

import java.util.List;

public interface ReminderService {
    Reminders createReminderDosage(int id, ReminderCreateRequest request);

    Reminders createReminderReExam(int id, ReminderCreateRequest request);

    Reminders getReminderById(int id);

    List<Reminders> getAllReminders();

    Reminders updateReminderDosage(int id, ReminderUpdateRequest request);

    Reminders updateReminderReExam(int id, ReminderUpdateRequest request);

    void deleteReminder(int id);

    void sendDueReminderReExam();

    Reminders getMyReminderByIdCus(int id);

    List<Reminders> getMyReminderByIdStaff(int id);
}