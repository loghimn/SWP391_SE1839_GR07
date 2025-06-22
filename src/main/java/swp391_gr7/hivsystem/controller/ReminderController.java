package swp391_gr7.hivsystem.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
    import swp391_gr7.hivsystem.model.Reminders;
    import swp391_gr7.hivsystem.service.JWTUtils;
    import swp391_gr7.hivsystem.service.ReminderService;

    import java.util.List;

    @RestController
    @RequestMapping("/api/reminders")
    public class ReminderController {

        @Autowired
        private ReminderService reminderService;

        @PostMapping("/create-dosage")
        public Reminders createReminderDosage(@RequestBody ReminderCreateRequest request,
                                              @RequestHeader("Authorization") String authorizationHeader) {
            // Extract staffId from the token
            String token = authorizationHeader.replace("Bearer ", "");
            int staffId = new JWTUtils().extractStaffId(token);

            return reminderService.createReminderDosage(staffId, request);
        }

        @PostMapping("/create-re-exam")
        public Reminders createReminderReExam(@RequestBody ReminderCreateRequest request,
                                              @RequestHeader("Authorization") String authorizationHeader) {
            // Extract staffId from the token
            String token = authorizationHeader.replace("Bearer ", "");
            int staffId = new JWTUtils().extractStaffId(token);

            return reminderService.createReminderReExam(staffId, request);
        }
        @GetMapping("/{id}")
        public Reminders getById(@PathVariable int id) {
            return reminderService.getReminderById(id);
        }

        @GetMapping
        public List<Reminders> getAll() {
            return reminderService.getAllReminders();
        }

        @PutMapping("/updateReminderDosage/{id}")
        public Reminders updateReminderDosage(@PathVariable int id, @RequestBody ReminderCreateRequest request) {
            return reminderService.updateReminderDosage(id, request);
        }
        @PutMapping("/updateReminderReExam/{id}")
        public Reminders updateReminderReExam(@PathVariable int id, @RequestBody ReminderCreateRequest request) {
            return reminderService.updateReminderReExam(id, request);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable int id) {
            reminderService.deleteReminder(id);
        }
    }