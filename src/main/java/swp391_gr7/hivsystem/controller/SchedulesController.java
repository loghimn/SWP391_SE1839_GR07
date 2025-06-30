
package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@SecurityRequirement(name = "bearerAuth")
public class SchedulesController {

    @Autowired
    private SchedulesService schedulesService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @PreAuthorize("hasRole('Manager')")
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
    @GetMapping("/doctor/get/my-schedules")
    public List<Schedules> getMySchedules(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return schedulesService.getMySchedules(doctor.getDoctorId());
    }
}