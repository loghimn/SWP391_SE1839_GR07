package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResultCreateRequest {
    private int appointmentId;
    private int treatmentPlanId;
    private boolean resultValue;
    private String notes;
    private boolean reExamination;
    private int CD4;
    private int hivViralLoad;
}