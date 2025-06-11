package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Reminder;

import java.util.List;

public interface ReminderService {
    public Reminder addReminder(ReminderCreateRequest request);
    public List<Reminder> getAllReminders();
    public void sendDueReminder();
}
