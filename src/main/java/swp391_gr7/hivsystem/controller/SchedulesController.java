
package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.SchedulesCreateRequest;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {

    @Autowired
    private SchedulesService schedulesService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @PostMapping("/create")
    public ApiResponse<Boolean> create(@RequestBody SchedulesCreateRequest request) {
        Schedules schedule = schedulesService.createSchedule(request);
        boolean result = schedule != null;
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Schedule created successfully" : "Failed to create schedule")
                .build();
    }


    @GetMapping("/{id}")
    public Optional<Schedules> getById(@PathVariable int id) {
        return schedulesService.getScheduleById(id);
    }

    @GetMapping
    public List<Schedules> getAll() {
        return schedulesService.getAllSchedules();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> update(@PathVariable int id, @RequestBody SchedulesCreateRequest request) {
        try {
            Doctors doctor = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            Managers manager = managerRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Manager not found"));

            Schedules schedule = Schedules.builder()
                    .scheduleID(id)
                    .doctors(doctor)
                    .managers(manager)
                    .workDate(request.getWorkDate())
                    .notes(request.getNotes())
                    .build();

            Schedules result = schedulesService.updateSchedule(id, schedule);
            boolean success = result != null;

            return ApiResponse.<Boolean>builder()
                    .code(success ? 200 : 400)
                    .result(success)
                    .message(success ? "Success" : "Update failed")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Boolean>builder()
                    .code(400)
                    .result(false)
                    .message("Update failed: " + e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        schedulesService.deleteSchedule(id);
    }
}