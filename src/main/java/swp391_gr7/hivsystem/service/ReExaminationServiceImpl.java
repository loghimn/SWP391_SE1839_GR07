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
        if (!testResult.isRe_examination()) return;

        Appointments originalAppointment = appointmentRepository
                .findById(testResult.getAppointments().getAppointmentId())
                .orElseThrow(() -> new AppException(ErrorCode.RE_EXAMINATION_ORIGINAL_APPOINTMENT_NOT_FOUND));

        Customers customers = testResult.getCustomers();
        Doctors doctors = testResult.getDoctors();
        LocalDateTime startSearchDate = originalAppointment.getStartTime().plusDays(7);

        // Lấy lịch làm việc của bác sĩ sau ngày tái khám dự kiến
        List<Schedules> availableSchedules = schedulesRepository
                .findByDoctors_DoctorId(doctors.getDoctorId()).stream()
                .filter(s -> {
                    LocalDate workDate = s.getWorkDate();
                    return !workDate.isBefore(startSearchDate.toLocalDate())
                            && !workDate.isAfter(startSearchDate.toLocalDate().plusDays(30));
                })
                .sorted(Comparator.comparing(Schedules::getWorkDate))
                .toList();


        if (availableSchedules.isEmpty()) {
            throw new AppException(ErrorCode.RE_EXAMINATION_SCHEDULE_NOT_FOUND_FOR_DOCTOR);
        }

        // Lấy tất cả appointment sắp tới của bác sĩ (trong vòng 30 ngày từ ngày tìm)
        List<Appointments> upcomingAppointments = appointmentRepository
                .findByDoctors_DoctorIdAndStartTimeBetween(
                        doctors.getDoctorId(),
                        startSearchDate.toLocalDate().atStartOfDay(),
                        startSearchDate.plusDays(30)
                );

        // Duyệt từng ngày bác sĩ làm việc và tìm giờ trống
        LocalDateTime selectedTime = null;
        Schedules selectedSchedule = null;

        outer:
        for (Schedules schedule : availableSchedules) {
            LocalDate workDate = schedule.getWorkDate();

            for (int hour = 8; hour <= 16; hour += 2) {
                LocalDateTime candidate = workDate.atTime(hour, 0);

                boolean hasConflict = upcomingAppointments.stream()
                        .anyMatch(a -> a.getStartTime().equals(candidate) && a.isStatus());

                if (!hasConflict) {
                    selectedTime = candidate;
                    selectedSchedule = schedule;
                    break outer;
                }
            }
        }

        if (selectedTime == null) {
            throw new AppException(ErrorCode.RE_EXAMINATION_SCHEDULE_NOT_FOUND_FOR_DOCTOR);
        }

        // Tạo lịch tái khám mới
        Appointments newAppointment = new Appointments();
        newAppointment.setCustomers(customers);
        newAppointment.setDoctors(doctors);
        newAppointment.setAppointmentType(testResult.getTestType());
        newAppointment.setStartTime(selectedTime);
        newAppointment.setEndTime(selectedTime.plusHours(2));
        newAppointment.setAnonymous(originalAppointment.isAnonymous());
        newAppointment.setMedicalRecords(originalAppointment.getMedicalRecords());
        newAppointment.setSchedules(selectedSchedule);
        newAppointment.setStatus(true); // hoặc pending
        newAppointment.setStaffs(originalAppointment.getStaffs());

        appointmentRepository.save(newAppointment);
    }


}
