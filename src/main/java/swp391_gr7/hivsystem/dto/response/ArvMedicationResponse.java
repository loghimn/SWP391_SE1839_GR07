package swp391_gr7.hivsystem.dto.response;

import lombok.Data;
import swp391_gr7.hivsystem.model.ArvMedications;

@Data
public class ArvMedicationResponse {
    private int arvMedicationID;
    private String code;
    private String name;
    private String form;
    private String strength;
    private String manufacturer;
    private String description;
    private boolean active;
    private String arvRegimentName;

    public ArvMedicationResponse(ArvMedications m) {
        this.arvMedicationID = m.getArvMedicationID();
        this.code = m.getCode();
        this.name = m.getName();
        this.form = m.getForm();
        this.strength = m.getStrength();
        this.manufacturer = m.getManufacturer();
        this.description = m.getDescription();
        this.active = m.isActive();

        this.arvRegimentName = (m.getArvRegiment() != null) ? m.getArvRegiment().getName() : null;
    }
}
