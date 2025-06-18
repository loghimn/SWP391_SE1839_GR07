package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndManagerCreateRequest extends CreateUserRequest{

    @NotBlank(message = "MANAGER_INVALID_DEPARTMENT_NOTBLANK")
    private String department;

    @NotBlank(message = "MANAGER_INVALID_OFFICE_PHONE_NOTBLANK")
    @Pattern(regexp = "^0\\d{9}$", message = "MANAGER_INVALID_OFFICE_PHONE_FORMAT")
    private String officePhone;

}
