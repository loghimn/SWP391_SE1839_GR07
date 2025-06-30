package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResultCreateRequest {
    // private int appointmentId;
    @NotNull(message = "TEST_RESULT_CREATE_REQUEST_TREATMENT_PLAN_ID_NOT_NULL")
    private int treatmentPlanId;

    @NotNull(message = "TEST_RESULT_CREATE_REQUEST_RESULT_VALUE_NOT_NULL")
    private boolean resultValue;

    @NotBlank(message = "TEST_RESULT_CREATE_REQUEST_NOTES_NOT_BLANK")
    private String notes;

    @NotNull(message = "TEST_RESULT_CREATE_REQUEST_RE_EXAMINATION_NOT_NULL")
    private boolean reExamination;

    @NotNull(message = "TEST_RESULT_CREATE_REQUEST_CD4_NOT_NULL")
    private int CD4;

    @NotNull(message = "TEST_RESULT_CREATE_REQUEST_HIV_VIRAL_LOAD_NOT_NULL")
    private int hivViralLoad;
}