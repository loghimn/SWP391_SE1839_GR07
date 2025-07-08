package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderReExamCreateRequest {
//    @NotNull(message = "CUSTOMER_ID_REQUIRED")
//    private Integer customerId;

    @NotBlank(message = "MESSAGE_REQUIRED")
    private String message;

    @NotNull(message = "APPOINTMENT_ID_REQUIRED")
    private Integer appointmentId;
}
