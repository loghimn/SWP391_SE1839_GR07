// src/main/java/swp391_gr7/hivsystem/dto/request/MedicalRecordCreateRequest.java
package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordCreateRequest {
    //  private int customerId;
    @NotBlank(message = "MEDICAL_RECORD_DOCTOR_INVALID_MAIL_NOTBLANK")
    private String diagnosis;

    @NotBlank(message = "MEDICAL_RECORD_TREATMENT_NOTBLANK")
    private String treatment;

    @NotNull(message = "MEDICAL_RECORD_NOTES_NOTBLANK")
    private LocalDate recordDate;
}