package swp391_gr7.hivsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleStatisticsResponse {
    private int month;
    private int year;
    private String role;
    private long total;
}
