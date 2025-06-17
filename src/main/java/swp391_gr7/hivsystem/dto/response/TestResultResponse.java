package swp391_gr7.hivsystem.dto.response;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResultResponse {
    private Long id;
    private String testType;
    private boolean resultValue;
    private LocalDate testDate;
    private String notes;
    private boolean reExamination;
    private Long appointmentId;
    private Long customerId;
    private Long doctorId;
}