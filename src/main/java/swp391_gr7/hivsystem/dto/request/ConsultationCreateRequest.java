package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationCreateRequest {

    @NotNull(message = "CONSULTATION_REQUEST_APPOINTMENT_NOT_NULL")
    private int appointmentId;

    @NotBlank(message = "CONSULTATION_REQUEST_NOTES_NOTBLANK")
    private String notes;
}