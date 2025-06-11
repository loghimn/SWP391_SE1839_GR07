package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
    Reminder save(Reminder reminder);
    @Query(value = "SELECT * FROM reminders r WHERE r.status = 0 AND r.reminder_time <= :reminderTime",
            nativeQuery = true)
    List<Reminder> findReminderStatusFalseAndReminderTimeBefore(@Param("reminderTime") LocalDateTime reminderTime);
}
