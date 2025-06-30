package swp391_gr7.hivsystem.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.model.TestResults;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArvRegimentCreateRequest {
    @NotBlank(message = "ARV_REGIMENT_NAME_NOT_BLANK")
    private String name;

    @NotNull(message = "ARV_REGIMENT_LEVEL_NOT_NULL")
    private int level;

    @NotBlank(message = "ARV_REGIMENT_DESCRIPTION_NOT_BLANK")
    private String description;
    //private int testResultId;
}
