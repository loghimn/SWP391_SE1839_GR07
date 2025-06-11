package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Get information create request form user
public class UserAndCustomerCreateRequest extends CreateUserRequest{
    private String address;
    //private String role; Setup role default
}
