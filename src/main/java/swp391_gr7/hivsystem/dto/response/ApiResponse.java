package swp391_gr7.hivsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Cau truc api json reponse
public class ApiResponse<T> {
    private int code;
    private String message;
    private T result;
}
