// src/main/java/swp391_gr7/hivsystem/dto/request/MedicalRecordCreateRequest.java
package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordCreateRequest {
    //  private int customerId;
    private String diagnosis;
    private String treatment;
    private LocalDate recordDate;
}