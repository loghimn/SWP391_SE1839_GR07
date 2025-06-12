package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateRequest {
    private int customerId;
    private int doctorId;
    private int staffId;
    private LocalDate appointmentTime;
    private boolean status;
    private boolean anonymous;
    private String appointmentType;
    private int scheduleId;

}