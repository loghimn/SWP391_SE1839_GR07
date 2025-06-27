package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "AUTHENTICATION_REQUEST_USERNAME_NOT_BLANK")
    private String username;

    @NotBlank(message = "AUTHENTICATION_REQUEST_PASSWORD_NOT_BLANK")
    private String password;
}
