package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.SchedulesCreateRequest;
import swp391_gr7.hivsystem.model.Schedules;
import java.util.List;
import java.util.Optional;

public interface SchedulesService {
    Schedules createSchedule(SchedulesCreateRequest request);
    Optional<Schedules> getScheduleById(int id);
    List<Schedules> getAllSchedules();
    Schedules updateSchedule(int id, Schedules schedule);
    void deleteSchedule(int id);
}