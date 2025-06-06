package swp391_gr7.hivsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//Get information login request
public class AuthenticationRequest {
    String username;
    String password;
}
