
package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.SchedulesCreateRequest;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
@SecurityRequirement(name = "bearerAuth")
public class SchedulesController {

    @Autowired
    private SchedulesService schedulesService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasRole('Manager')")
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@RequestBody @Valid SchedulesCreateRequest request) {
        Schedules schedule = schedulesService.createSchedule(request);
        boolean result = schedule != null;
        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Schedule created successfully" : "Failed to create schedule")
                .build();
    }


    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/{id}")
    public Optional<Schedules> getById(@PathVariable int id) {
        return schedulesService.getScheduleById(id);
    }

    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/all")
    public List<Schedules> getAll() {
        return schedulesService.getAllSchedules();
    }

    @PreAuthorize("hasRole('Manager')")
    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> update(@PathVariable int id, @RequestBody SchedulesCreateRequest request) {
        try {
            Schedules updatedSchedule = schedulesService.updateSchedule(id, request);
            boolean success = updatedSchedule != null;
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

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment/{day}")
    public ApiResponse<List<Appointments>> getAppointmentsByDay(@PathVariable LocalDate day) {
        List<Appointments> appointmentsList = appointmentService.getAppointmentsByDay(day);
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List<Appointments>>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

}