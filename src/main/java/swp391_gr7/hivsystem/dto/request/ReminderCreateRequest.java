package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderCreateRequest {
    // private int id; vi id tu generate ra
    private String staffMail; // nguoi gui mail
    private String customerMail; // nguoi nhan mail
    private String reminderType; // loai nhac nho
    private LocalDate reminderTime; // thoi gian nhac
    private String message; // tin nhan content nhac nho
    private boolean status; // tinh trang gui: da gui, chua gui

}
