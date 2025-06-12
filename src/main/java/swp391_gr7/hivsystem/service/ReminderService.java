package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Reminders;

import java.util.List;

public interface ReminderService {
    public Reminders addReminder(ReminderCreateRequest request);
    public List<Reminders> getAllReminders();
    public void sendDueReminder();
}
