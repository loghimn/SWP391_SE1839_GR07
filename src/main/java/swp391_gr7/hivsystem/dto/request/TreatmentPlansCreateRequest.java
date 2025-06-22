package swp391_gr7.hivsystem.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentPlansCreateRequest {
    private int doctorId;
    private String TreatmentPlanDescription;
    private boolean isForPregnancy;
    private LocalTime dosageTime;
    private int customerId;
    private boolean isHistory;
}
