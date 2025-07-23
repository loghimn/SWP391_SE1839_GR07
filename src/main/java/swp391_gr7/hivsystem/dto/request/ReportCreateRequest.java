package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCreateRequest {

    @NotBlank(message = "REPORT_CREATE_REQUEST_TYPE_NOT_BLANK")
    private String reportType;

    @NotBlank(message = "REPORT_CREATE_REQUEST_FILE_TYPE_NOT_BLANK")
    private String fileType;

    @NotNull(message = "REPORT_CREATE_REQUEST_MONTH_NOT_NULL")
    @Min(value = 1, message = "REPORT_CREATE_REQUEST_MONTH_MIN_1")
    @Max(value = 12, message = "REPORT_CREATE_REQUEST_MONTH_MAX_12")
    private int month;

    @NotNull(message = "REPORT_CREATE_REQUEST_YEAR_NOT_NULL")
    private int year;

}
