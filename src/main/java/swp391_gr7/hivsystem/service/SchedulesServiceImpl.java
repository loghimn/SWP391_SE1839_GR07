package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.SchedulesCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Schedules;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.SchedulesRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulesServiceImpl implements SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Schedules createSchedule(SchedulesCreateRequest request) {
        Doctors doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_DOCTOR_NOT_FOUND));
        Managers manager = managerRepository.findById(1)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_MANAGER_NOT_FOUND));

        if (request.getWorkDate().isBefore(LocalDate.now())) {
            throw new AppException(ErrorCode.SCHEDULE_INVALID_DATE);
        }
        Schedules schedule = new Schedules();
        schedule.setDoctors(doctor);
        schedule.setManagers(manager);
        schedule.setWorkDate(request.getWorkDate());
        schedule.setNotes(request.getNotes());
        return schedulesRepository.save(schedule);
    }

    @Override
    public Optional<Schedules> getScheduleById(int id) {
        if(schedulesRepository.findById(id) == null) {
            throw new AppException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        return schedulesRepository.findById(id);
    }

    @Override
    public List<Schedules> getAllSchedules() {
        if(schedulesRepository.findAll() == null) {
            throw new AppException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        return schedulesRepository.findAll();
    }

    @Override
    public Schedules updateSchedule(int id, SchedulesCreateRequest request) {
        Schedules existingSchedule = schedulesRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_FOUND));

        existingSchedule.setWorkDate(request.getWorkDate());
        return schedulesRepository.save(existingSchedule);
    }

    @Override
    public List<Schedules> getMySchedules(int doctorId) {
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_DOCTOR_NOT_FOUND));
        return schedulesRepository.findByDoctors(doctor);
    }
}