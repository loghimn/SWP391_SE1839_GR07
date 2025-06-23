package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Reminders;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminders, Integer> {
    @Query(value = "SELECT * FROM reminders r WHERE r.status = 0 AND r.reminder_time <= :reminderTime AND r.reminder_type = 'Re-Exam Reminder'",
            nativeQuery = true)
    List<Reminders> findReminderStatusFalseAndReminderTimeBefore(@Param("reminderTime") LocalDateTime reminderTime);

    Reminders findRemindersByCustomersCustomerId(int customersCustomerId);
}
