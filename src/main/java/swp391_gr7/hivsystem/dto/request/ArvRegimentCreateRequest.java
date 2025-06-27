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
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "ARV regiment type is mandatory")
    private int level;

    @NotBlank(message = "Description is mandatory")
    private String description;
    //private int testResultId;
}
