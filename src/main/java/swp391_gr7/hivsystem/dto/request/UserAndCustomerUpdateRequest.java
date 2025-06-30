package swp391_gr7.hivsystem.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.validator.ValidProvinceConstraint;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndCustomerUpdateRequest extends UserUpdateRequest{

    @NotBlank(message = "CUSTOMER_UPDATE_INVALID_ADDRESS_NOTBLANK")
    @ValidProvinceConstraint(message = "CUSTOMER_UPDATE_INVALID_ADDRESS")
    private String address;
}
