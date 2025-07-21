package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.validator.DateOfBirthConstraint;
import swp391_gr7.hivsystem.validator.ValidProvinceConstraint;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2CreateRequest {

    @NotBlank(message = "USER_INVALID_EMAIL_NOTBLANK")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@((gmail)\\.(com)|(example)\\.(com|vn|org)|(fpt\\.edu(\\.vn)?))$", message = "USER_INVALID_EMAIL_FORMAT")
    private String email;

    @NotBlank(message = "USER_INVALID_USERNAME_NOTBLANK")
    @Size(min = 5, message = "USER_INVALID_USERNAME_SIZE")
    private String username;

    @NotBlank(message = "USER_INVALID_PASSWORD_NOTBLANK")
    @Size(min = 8, message = "USER_INVALID_PASSWORD_SIZE")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[^a-z\\d]).+$", message = "USER_INVALID_PASSWORD_FORMAT")
    private String password;

    @NotBlank(message = "USER_INVALID_FULLNAME_NOTBLANK")
    @Size(min = 6, message = "USER_INVALID_FULLNAME_SIZE")
    private String fullName;

    @NotBlank(message = "USER_INVALID_PHONE_NOTBLANK")
    @Size(min = 10, max = 10, message = "USER_INVALID_PHONE_SIZE")
    @Pattern(regexp = "^0[0-9]{9}$", message = "USER_INVALID_PHONE_FORMAT")
    private String phone;

    @NotNull(message = "USER_DATEOFBIRTH_NOTNULL")
    @DateOfBirthConstraint(minAge = 18, message = "USER_INVALID_DATEOFBIRTH")
    private LocalDate dateOfBirth;

    @NotBlank(message = "USER_INVALID_GENDER_NOTBLANK")
    @Pattern(regexp = "^(male|female)$", message = "USER_INVALID_GENDER_FORMAT")
    private String gender;

    @NotBlank(message = "CUSTOMER_INVALID_ADDRESS_NOTBLANK")
    @ValidProvinceConstraint(message = "CUSTOMER_INVALID_ADDRESS")
    private String address;
}
