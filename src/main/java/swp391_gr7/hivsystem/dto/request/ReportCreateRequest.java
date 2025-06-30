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

    @NotBlank(message = "REPORT_CREATE_REQUEST_TYPE_NOT_BLANK")
    private String reportType;

}
