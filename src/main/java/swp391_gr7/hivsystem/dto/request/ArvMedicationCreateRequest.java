package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArvMedicationCreateRequest {
    private String code;
    private String name;
    private String form;
    private String strength;
    private String manufacturer;
    private String description;
    private String arvRegimentName;
}
