package swp391_gr7.hivsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Get token
//Not yet
public class IntrospectRequest {
    String token;
}
