package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequest {
    @NotBlank(message = "OLD_PASSWORD_NOT_BLANK")
    private String oldPassword;

    @NotBlank(message = "NEW_PASSWORD_NOT_BLANK")
    @Size(min = 8, message = "USER_UPDATE_INVALID_PASSWORD_SIZE")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[^a-z\\d]).+$", message = "USER_UPDATE_INVALID_PASSWORD_FORMAT")
    private String newPassword;
}