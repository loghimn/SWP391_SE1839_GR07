package swp391_gr7.hivsystem.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Response when authentication successfully
public class AuthenticationReponse {
    private String token;
    boolean authenticated;
}
