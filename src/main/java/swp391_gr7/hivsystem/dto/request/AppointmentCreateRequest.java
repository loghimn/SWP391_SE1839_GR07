package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateRequest {
    //private int appointmentID;
    private String customerMail;
    private String doctorsName;
    private LocalDate appointmentTime;
    private boolean status;
    private boolean anonymous;
}
