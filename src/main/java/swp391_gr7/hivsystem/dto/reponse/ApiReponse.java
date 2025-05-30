package swp391_gr7.hivsystem.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiReponse <T>{
    private int code;
    private String message;
    private T result;
}
