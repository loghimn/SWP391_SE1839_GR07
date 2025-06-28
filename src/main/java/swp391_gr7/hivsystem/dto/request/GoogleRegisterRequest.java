package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleRegisterRequest {
    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_USERNAME_NOT_BLANK")
    private String username;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_PASSWORD_NOT_BLANK")
    private String password;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_EMAIL_NOT_BLANK")
    private String email;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_PHONE_NOT_BLANK")
    private String phone;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_FULL_NAME_NOT_BLANK")
    private String fullName;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_DATE_OF_BIRTH_NOT_BLANK")
    private String dateOfBirth;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_GENDER_NOT_BLANK")
    private String gender;

    @NotBlank(message = "GOOGLE_REGISTER_REQUEST_ADDRESS_NOT_BLANK")
    private String address;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

}