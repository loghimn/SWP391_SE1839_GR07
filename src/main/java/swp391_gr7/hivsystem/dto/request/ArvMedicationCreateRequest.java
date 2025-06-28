package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArvMedicationCreateRequest {

    @NotBlank(message = "ARV_MEDICATION_REQUEST_CODE_NOT_BLANK")
    private String code;

    @NotBlank(message = "ARV_MEDICATION_REQUEST_NAME_NOT_BLANK")
    private String name;

    @NotBlank(message = "ARV_MEDICATION_REQUEST_FORM_NOT_BLANK")
    private String form;

    @NotBlank(message = "ARV_MEDICATION_REQUEST_STRENGTH_NOT_BLANK")
    private String strength;

    @NotBlank(message = "ARV_MEDICATION_REQUEST_MANUFACTURER_NOT_BLANK")
    private String manufacturer;

    @NotBlank(message = "ARV_MEDICATION_REQUEST_DESCRIPTION_NOT_BLANK")
    private String description;

    @NotBlank(message = "ARV_MEDICATION_REQUEST_REGIMENT_NAME_NOT_BLANK")
    private String arvRegimentName;
}
