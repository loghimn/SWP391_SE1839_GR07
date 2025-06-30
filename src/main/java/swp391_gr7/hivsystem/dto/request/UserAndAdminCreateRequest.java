package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserAndAdminCreateRequest extends UserCreateRequest {

    @NotBlank(message = "USER_AND_ADMIN_UPDATE_REQUEST_ASSIGNED_AREA_NOT_BLANK")
    private String assignedArea;
}
