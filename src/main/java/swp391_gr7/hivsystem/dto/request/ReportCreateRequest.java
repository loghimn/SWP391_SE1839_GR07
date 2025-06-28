package swp391_gr7.hivsystem.dto.request;

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
    //    private String managerMail;

    @NotBlank(message = "REPORT_CREATE_REQUEST_TITLE_NOT_BLANK")
    private String reportType;

    @NotNull(message = "REPORT_CREATE_REQUEST_CONTENT_NOT_BLANK")
    private LocalDateTime createAt;
}
