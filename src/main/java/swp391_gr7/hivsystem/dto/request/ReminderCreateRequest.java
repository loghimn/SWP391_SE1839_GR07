package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReminderCreateRequest {
    @NotNull(message = "CUSTOMER_ID_REQUIRED")
    private Integer customerId;

//    @NotBlank(message = "REMINDER_TYPE_REQUIRED")
//    private String reminderType;

    @NotNull(message = "REMINDER_TIME_REQUIRED")
    private LocalDateTime reminderTime;

    @NotBlank(message = "MESSAGE_REQUIRED")
    private String message;

//    @NotNull(message = "STATUS_REQUIRED")
//    private Boolean status;

    //  Cai này có thể không cần thiết, vì staffId có thể được lấy từ thông tin người dùng đã đăng nhập
//    @NotNull(message = "STAFF_ID_REQUIRED")
//    private Integer staffId;

    @NotNull(message = "TEST_RESULT_ID_REQUIRED")
    private Integer testResultId;

    @NotNull(message = "APPOINTMENT_ID_REQUIRED")
    private Integer appointmentId;
}