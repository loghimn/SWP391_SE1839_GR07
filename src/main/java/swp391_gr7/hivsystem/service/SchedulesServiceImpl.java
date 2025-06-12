package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.model.Schedules;
import swp391_gr7.hivsystem.repository.SchedulesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SchedulesServiceImpl implements SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Override
    public Schedules createSchedule(Schedules schedule) {
        return schedulesRepository.save(schedule);
    }

    @Override
    public Optional<Schedules> getScheduleById(int id) {
        return schedulesRepository.findById(id);
    }

    @Override
    public List<Schedules> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    @Override
    public Schedules updateSchedule(int id, Schedules schedule) {
        schedule.setScheduleID(id);
        return schedulesRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(int id) {
        schedulesRepository.deleteById(id);
    }
}