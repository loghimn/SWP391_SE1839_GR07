package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationUpdateRequest {
    @NotBlank(message = "CONSULTATION_REQUEST_NOTES_NOTBLANK")
    private String notes;
}
