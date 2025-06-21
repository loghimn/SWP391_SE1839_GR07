package swp391_gr7.hivsystem.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
    import swp391_gr7.hivsystem.model.Reminders;
    import swp391_gr7.hivsystem.service.ReminderService;

    import java.util.List;

    @RestController
    @RequestMapping("/api/reminders")
    public class ReminderController {

        @Autowired
        private ReminderService reminderService;

        @PostMapping
        public Reminders create(@RequestBody ReminderCreateRequest request) {
            return reminderService.createReminder(request);
        }

        @GetMapping("/{id}")
        public Reminders getById(@PathVariable int id) {
            return reminderService.getReminderById(id);
        }

        @GetMapping
        public List<Reminders> getAll() {
            return reminderService.getAllReminders();
        }

        @PutMapping("/{id}")
        public Reminders update(@PathVariable int id, @RequestBody ReminderCreateRequest request) {
            return reminderService.updateReminder(id, request);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable int id) {
            reminderService.deleteReminder(id);
        }
    }