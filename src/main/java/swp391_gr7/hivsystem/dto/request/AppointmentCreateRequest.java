package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateRequest {
//    @NotNull(message = "APPOINTMENT_REQUEST_CUSTOMER_NOTNULL")
//    private int customerId;

    @NotNull(message = "APPOINTMENT_REQUEST_DOCTOR_NOTNULL")
    private String doctorName;

//    @NotNull(message = "APPOINTMENT_REQUEST_STAFF_NOTNULL")
//    private int staffId;

    @NotNull(message = "APPOINTMENT_REQUEST_TIME_NOTNULL")
    private LocalDate appointmentTime;

//    private boolean status;
    @NotNull(message = "APPOINTMENT_REQUEST_ANONYMOUS_NOTNULL")
    private boolean anonymous;

    @NotBlank(message = "APPOINTMENT_REQUEST_TYPE_NOTBLANK")
    @Pattern(regexp = "^(Test HIV|Consultation)$", message = "APPOINTMENT_REQUEST_TYPE_INVALID_FORMAT")
    private String appointmentType;

    @NotNull(message = " APPOINTMENT_REQUEST_SCHEDULE_NOTNULL")
    private int scheduleId;
}