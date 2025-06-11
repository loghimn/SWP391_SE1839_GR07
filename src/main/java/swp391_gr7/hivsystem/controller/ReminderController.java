package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.request.ReminderCreateRequest;
import swp391_gr7.hivsystem.model.Reminder;
import swp391_gr7.hivsystem.service.ReminderService;
import swp391_gr7.hivsystem.service.ReminderServiceImp;

import java.util.List;

@RestController
// đường dẫn chung, tất cả đều bắt đầu bằng /api
@RequestMapping("/api")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    // đường dẫn đến tạo lịch nhắc (reminder)
    @PostMapping("/reminder/create")
    public ApiReponse<Boolean> reminderCreate(@RequestBody ReminderCreateRequest request) {
        Reminder reminder = reminderService.addReminder(request); // gọi từ service
        // kiểm tra reminder có null không, rồi gán vào biến result
        boolean result = reminder != null;

        if(reminder == null){
            ReminderServiceImp reminderServiceImp = new ReminderServiceImp();
            // in ra lỗi đã thêm vào biến error ở bên ReminderServiceImp
            if(reminderServiceImp.error != null){
                return  ApiReponse.<Boolean>builder()
                        .result(result)
                        .message(reminderServiceImp.error)
                        .build();
            }
        }
        // Nếu tạo thành công, trả về tin nhắn success
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    // In ra danh sách Reminder có trong database
    @GetMapping("/reminder/list")
    public ApiReponse<List> reminderList() {
        List<Reminder> remindersList = reminderService.getAllReminders(); // Được gọi từ service
        boolean result = remindersList != null;
        String resultString = result ? "Success" : "Error";
        return ApiReponse.<List>builder()
                .result(remindersList)
                .message(resultString)
                .build();
    }

    // Gửi Email theo lịch trong database
    @PostMapping("/reminder/send")
    public ApiReponse<Boolean> sendReminder(){
        reminderService.sendDueReminder();
        return ApiReponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }
}
