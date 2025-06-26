package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Reminders;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.ReminderService;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@SecurityRequirement(name = "bearerAuth")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PreAuthorize("hasRole('Staff')")
    @PostMapping("/create-dosage")
    public Reminders createReminderDosage(@RequestBody ReminderCreateRequest request,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        // Extract staffId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int staffId = new JWTUtils().extractStaffId(token);

        return reminderService.createReminderDosage(staffId, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @PostMapping("/create-re-exam")
    public Reminders createReminderReExam(@RequestBody ReminderCreateRequest request,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        // Extract staffId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int staffId = new JWTUtils().extractStaffId(token);

        return reminderService.createReminderReExam(staffId, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @GetMapping("/{id}")
    public Reminders getById(@PathVariable int id) {
        return reminderService.getReminderById(id);
    }

    @PreAuthorize("hasRole('Staff')")
    @GetMapping
    public List<Reminders> getAll() {
        return reminderService.getAllReminders();
    }

    @PreAuthorize("hasRole('Staff')")
    @PutMapping("/updateReminderDosage/{id}")
    public Reminders updateReminderDosage(@PathVariable int id, @RequestBody ReminderCreateRequest request) {
        return reminderService.updateReminderDosage(id, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @PutMapping("/updateReminderReExam/{id}")
    public Reminders updateReminderReExam(@PathVariable int id, @RequestBody ReminderCreateRequest request) {
        return reminderService.updateReminderReExam(id, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @PutMapping("/deleteReminder/{id}")
    public void deleteReminder(@PathVariable int id) {
        reminderService.deleteReminder(id);
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/my-reminder")
    public Reminders getMyReminderById(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);
        return reminderService.getMyReminderById(customerId);
    }
}