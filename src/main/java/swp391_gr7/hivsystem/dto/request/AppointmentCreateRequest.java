package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.Doctor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateRequest {
    private int appointmentID;
    private String customerMail;
    private String doctorsName;
    private LocalDate appointmentTime;
    private boolean status;
}
