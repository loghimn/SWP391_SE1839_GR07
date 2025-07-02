package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.validator.DateOfBirthConstraint;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Get information update request form user
public class UserUpdateRequest {

    @NotBlank(message = "USER_UPDATE_INVALID_USERNAME_SIZE")
    @Size(min = 5, message = "USER_UPDATE_INVALID_USERNAME_NOTBLANK")
    private String username;

//    @NotBlank(message = "USER_UPDATE_INVALID_PASSWORD_NOTBLANK")
//    @Size(min = 8, message = "USER_UPDATE_INVALID_PASSWORD_SIZE")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[^a-z\\d]).+$", message = "USER_UPDATE_INVALID_PASSWORD_FORMAT")
//    private String password;

    @NotBlank(message = "USER_UPDATE_INVALID_EMAIL_NOTBLANK")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(gmail|example)\\.(com|vn|org)$", message = "USER_UPDATE_INVALID_EMAIL_FORMAT")
    private String email;

    @NotBlank(message = "USER_UPDATE_INVALID_PHONE_NOTBLANK")
    @Size(min = 10, max = 10, message = "USER_UPDATE_INVALID_PHONE_SIZE")
    @Pattern(regexp = "^0[0-9]{9}$", message = "USER_UPDATE_INVALID_PHONE_FORMAT")
    private String phone;

    @NotBlank(message = "USER_UPDATE_INVALID_FULLNAME_NOTBLANK")
    private String fullName;

    @NotNull(message = "USER_UPDATE_DATEOFBIRTH_NOTNULL")
    @DateOfBirthConstraint(minAge = 1, message = "USER_UPDATE_INVALID_DATEOFBIRTH")
    private LocalDate dateOfBirth;

    @NotBlank(message = "USER_UPDATE_INVALID_GENDER_NOTBLANK")
    @Pattern(regexp = "^(male|female)$", message = "USER_UPDATE_INVALID_GENDER_FORMAT")
    private String gender;
}