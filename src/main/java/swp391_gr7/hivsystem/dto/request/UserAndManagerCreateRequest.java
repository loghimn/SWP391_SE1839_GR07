package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndManagerCreateRequest extends UserCreateRequest {

    @NotBlank(message = "MANAGER_INVALID_OFFICE_PHONE_NOTBLANK")
    @Pattern(regexp = "^0\\d{9}$", message = "MANAGER_INVALID_OFFICE_PHONE_FORMAT")
    private String officePhone;

}
