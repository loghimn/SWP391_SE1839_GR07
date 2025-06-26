package swp391_gr7.hivsystem.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulesCreateRequest {
    private Integer doctorId;
    //   private Integer managerId;
    private LocalDate workDate;
    private String notes;
}