package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.SchedulesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ReExaminationServiceImpl implements ReExaminationService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Override
    public void handleReExamination(TestResults testResult) {
        if (testResult.isRe_examination()) {
            Appointments originalAppointment = appointmentRepository
                    .findById(testResult.getAppointments().getAppointmentId())
                    .orElseThrow(() -> new AppException(ErrorCode.RE_EXAMINATION_ORIGINAL_APPOINTMENT_NOT_FOUND));

            Customers customers = testResult.getCustomers();
            Doctors doctors = testResult.getDoctors();
            LocalDateTime currentDateTime = originalAppointment.getStartTime().plusDays(7);

            // lấy list appointment
            List<Appointments> listAppointments = appointmentRepository.findAll();
            if (listAppointments.isEmpty()) {
                throw new AppException(ErrorCode.APPOINTMENT_LIST_NOT_FOUND);
            }
            int daysChecked = 0;
            int maxDays = 30;
            boolean found = false;

            while (daysChecked < maxDays) {
                LocalDate dateToCheck = currentDateTime.toLocalDate();

                // Kiểm tra các khung giờ trong ngày: 8h, 10h, 12h, 14h, 16h
                for (int hour = 8; hour <= 16; hour += 2) {
                    LocalDateTime candidate = dateToCheck.atTime(hour, 0);

                    boolean isDuplicate = listAppointments.stream()
                            .anyMatch(a -> a.getStartTime().equals(candidate) && a.isStatus());

                    if (!isDuplicate) {
                        currentDateTime = candidate; // Gán lại thời điểm phù hợp
                        found = true;
                        break;
                    }
                }

                if (found) break;

                currentDateTime = currentDateTime.plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);
                daysChecked++;
            }


            Schedules schedule = originalAppointment.getSchedules();


            // Lấy ngày bác sĩ làm việc
            List<Schedules> schedules = schedulesRepository.findByDoctors_DoctorId((doctors.getDoctorId()));
            if (schedules == null || schedules.isEmpty()) {
                throw new AppException(ErrorCode.RE_EXAMINATION_SCHEDULE_NOT_FOUND_FOR_DOCTOR);
            }

            LocalDateTime upperBound = currentDateTime.plusDays(30);

            LocalDateTime finalCurrentDate = currentDateTime;
            Schedules nextSchedule = schedules.stream()
                    .filter(s -> {
                        LocalDate workDate = s.getWorkDate();
                        return !workDate.isBefore(finalCurrentDate.toLocalDate()) && !workDate.isAfter(upperBound.toLocalDate());
                    })
                    .min(Comparator.comparing(Schedules::getWorkDate))
                    .orElseThrow(() -> new AppException(ErrorCode.RE_EXAMINATION_NO_SCHEDULE_DOCTOR_FOUND_AFTER_ORIGINAL_APPOINTMENT_DATE));

            // Tạo lịch tái khám mới
            Appointments appointment = new Appointments();
            appointment.setCustomers(customers);
            appointment.setDoctors(doctors);
            appointment.setAppointmentType(testResult.getTestType());
            appointment.setStartTime(nextSchedule.getWorkDate().atTime(LocalTime.from(currentDateTime)));
            appointment.setEndTime(nextSchedule.getWorkDate().atTime(LocalTime.from(currentDateTime)).plusHours(2));
            appointment.setAnonymous(originalAppointment.isAnonymous());
            appointment.setMedicalRecords(originalAppointment.getMedicalRecords());
            appointment.setSchedules(nextSchedule);
            appointment.setStatus(true); // pending hoặc true nếu là boolean
            appointment.setStaffs(originalAppointment.getStaffs());

            appointmentRepository.save(appointment);
        }
    }


}
