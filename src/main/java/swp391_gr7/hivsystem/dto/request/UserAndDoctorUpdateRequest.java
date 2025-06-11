package swp391_gr7.hivsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndDoctorUpdateRequest extends UserUpdateRequest{
    private String department;
    private int yearExperience;
    private String licenseNumber;
}
