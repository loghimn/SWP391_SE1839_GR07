package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndStaffCreateRequest extends UserCreateRequest {
    @NotBlank(message = "STAFF_INVALID_DEPARTMENT_NOTBLANK")
    private String department;

    @NotNull(message = "STAFF_INVALID_WORKSHIFT_NOTNULL")
    @Min(value = 1, message = "STAFF_INVALID_WORKSHIFT")
    @Max(value = 3, message = "STAFF_INVALID_WORKSHIFT")
    private int workShift;

    @NotBlank(message = "STAFF_INVALID_ASSIGNED_AREA_NOTBLANK")
    private String assignedArea;
}
