package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.validator.ValidProvinceConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Get information create request form user
public class UserAndCustomerCreateRequest extends UserCreateRequest {

    @NotBlank(message = "CUSTOMER_INVALID_ADDRESS_NOTBLANK")
    @ValidProvinceConstraint(message = "CUSTOMER_INVALID_ADDRESS")
    private String address;
}
