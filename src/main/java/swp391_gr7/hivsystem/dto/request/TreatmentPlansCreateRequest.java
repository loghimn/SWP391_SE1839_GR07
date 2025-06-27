package swp391_gr7.hivsystem.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentPlansCreateRequest {
    //  private int doctorId;

    @NotNull(message = "TREATMENT_PLAN_REQUEST_APPOINTMENT_NOT_NULL")
    private int appointmentId;

    @NotBlank(message = "TREATMENT_PLAN_REQUEST_START_DATE_NOT_NULL")
    private String treatmentPlanDescription;

    @NotNull(message = "TREATMENT_PLAN_REQUEST_START_DATE_NOT_NULL")
    private boolean forPregnancy;

    @NotNull(message = "TREATMENT_PLAN_REQUEST_START_DATE_NOT_NULL")
    private LocalTime dosageTime;
    //  private int customerId;

    @NotNull(message = "TREATMENT_PLAN_REQUEST_START_DATE_NOT_NULL")
    private boolean history;
}
