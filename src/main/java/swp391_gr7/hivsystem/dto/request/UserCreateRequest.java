package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    private String username;
    private String passwordHash;
    private String email;
    private String phone;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String role;
}
