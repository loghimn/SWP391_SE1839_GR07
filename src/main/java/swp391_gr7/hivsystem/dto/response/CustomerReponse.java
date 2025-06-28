package swp391_gr7.hivsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReponse {
    //private String customerName;
    private String fullName;
    private String email;
    private String gender;
    private String phone;
    private String address;

}