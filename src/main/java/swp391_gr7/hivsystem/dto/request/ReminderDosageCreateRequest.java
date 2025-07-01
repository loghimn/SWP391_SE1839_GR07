package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderDosageCreateRequest {
    @NotNull(message = "CUSTOMER_ID_REQUIRED")
    private Integer customerId;

    @NotBlank(message = "MESSAGE_REQUIRED")
    private String message;

    @NotNull(message = "TEST_RESULT_ID_REQUIRED")
    private Integer testResultId;
}