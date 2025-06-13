package swp391_gr7.hivsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2LoginResponse {
    private boolean authenticated;
    private String email;
    private String userName;
}