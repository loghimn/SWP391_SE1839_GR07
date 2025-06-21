package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Reminders;
import java.util.List;

public interface ReminderService {
    Reminders createReminder(ReminderCreateRequest request);
    Reminders getReminderById(int id);
    List<Reminders> getAllReminders();
    Reminders updateReminder(int id, ReminderCreateRequest request);
    void deleteReminder(int id);
}