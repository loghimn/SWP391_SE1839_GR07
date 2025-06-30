package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulesCreateRequest {

    @NotNull(message = "SCHEDULES_CREATE_REQUEST_DOCTOR_ID_NOT_NULL")
    private Integer doctorId;

    //   private Integer managerId;

    @NotNull(message = "SCHEDULES_CREATE_REQUEST_WORK_DATE_NOT_NULL")
    private LocalDate workDate;

    @NotBlank(message = "SCHEDULES_CREATE_REQUEST_NOTES_NOT_BLANK")
    private String notes;
}