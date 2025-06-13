package swp391_gr7.hivsystem.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestResultCreateRequest {
    private String notes;
    private boolean reExamination;
    private String resultValue;
    private LocalDate testDate;
    private String testType;
    private int appointmentID;
    private int customerID;
    private int doctorID;
    private int treatmentPlanID;
}
