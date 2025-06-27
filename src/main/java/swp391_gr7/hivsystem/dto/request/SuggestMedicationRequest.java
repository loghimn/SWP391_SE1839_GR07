package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestMedicationRequest {
    @NotNull(message = "SUGGEST_MEDICATION_REQUEST_TEST_RESULT_ID_NOT_NULL")
    int testResultId;

    @NotNull(message = "SUGGEST_MEDICATION_REQUEST_TREATMENT_PLANS_ID_NOT_NULL")
    int treatmentPlansId;
}
