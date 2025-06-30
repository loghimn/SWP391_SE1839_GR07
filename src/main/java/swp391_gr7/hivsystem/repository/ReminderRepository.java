package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Reminders;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminders, Integer> {
    @Query("SELECT r FROM Reminders r " +
            "JOIN FETCH r.customers c " +
            "JOIN FETCH c.users u " +
            "JOIN FETCH r.staffs s " +
            "JOIN FETCH s.users su " +
            "WHERE r.status = true AND r.reminderTime <= :reminderTime AND r.reminderType = 'Re-Exam Reminder'")
    List<Reminders> findReminderStatusFalseAndReminderTimeBefore(@Param("reminderTime") LocalDateTime reminderTime);

    Reminders findRemindersByCustomersCustomerId(int customersCustomerId);

    List<Reminders> findRemindersByStaffsStaffId(int staffsStaffId);
}
