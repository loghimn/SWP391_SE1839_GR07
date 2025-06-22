package swp391_gr7.hivsystem.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndDoctorUpdateRequest extends UserUpdateRequest{

    @NotBlank(message = "DOCTOR_UPDATE_INVALID_DEPARTMENT_NOTBLANK")
    private String department;

    @NotNull(message = "DOCTOR_UPDATE_INVALID_YEAR_EXPERIENCE_NOTNULL")
    @Min(value = 0, message = "DOCTOR_UPDATE_INVALID_YEAR_EXPERIENCE")
    private int yearExperience;

    @NotBlank(message = "DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_NOTBLANK")
    @Pattern(regexp = "^DC-\\d{4}$", message = "DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_FORMAT")
    private String licenseNumber;
}
