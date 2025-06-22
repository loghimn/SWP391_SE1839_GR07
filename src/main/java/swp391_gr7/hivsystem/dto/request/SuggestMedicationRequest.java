package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestMedicationRequest {
    int testResultId;
    int treatmentPlansId;
}
