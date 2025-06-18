package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2CreateRequest {
    private String email;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String dateOfBirth;
    private String gender;
    private String address;
}
