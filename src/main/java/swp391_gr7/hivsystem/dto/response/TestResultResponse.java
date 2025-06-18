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
    private int id;
    private String testType;
    private boolean resultValue;
    private LocalDate testDate;
    private String notes;
    private boolean reExamination;
    private int appointmentId;
    private int customerId;
    private int doctorId;
}