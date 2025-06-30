package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.dto.request.ReminderUpdateRequest;
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
    @PostMapping("/dosage/create")
    public Reminders createReminderDosage(@RequestBody ReminderCreateRequest request,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        // Extract staffId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int staffId = new JWTUtils().extractStaffId(token);

        return reminderService.createReminderDosage(staffId, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @PostMapping("/re-exam/create")
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
    @GetMapping("/get/all")
    public List<Reminders> getAll() {
        return reminderService.getAllReminders();
    }

    @PreAuthorize("hasRole('Staff')")
    @PutMapping("/dosage/update/{id}")
    public Reminders updateReminderDosage(@PathVariable int id, @RequestBody ReminderUpdateRequest request) {
        return reminderService.updateReminderDosage(id, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @PutMapping("/re-exam/update/{id}")
    public Reminders updateReminderReExam(@PathVariable int id, @RequestBody ReminderUpdateRequest request) {
        return reminderService.updateReminderReExam(id, request);
    }

    @PreAuthorize("hasRole('Staff')")
    @PutMapping("/delete/{id}")
    public void deleteReminder(@PathVariable int id) {
        reminderService.deleteReminder(id);
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/customer/get/my-reminder")
    public Reminders getMyReminderCustomer(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);
        return reminderService.getMyReminderByIdCus(customerId);
    }

    @PreAuthorize("hasRole('Staff')")
    @GetMapping("/staff/get/my-reminder")
    public List<Reminders> getMyReminderStaff(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int staffId = new JWTUtils().extractStaffId(token);

        return reminderService.getMyReminderByIdStaff(staffId);
    }
}