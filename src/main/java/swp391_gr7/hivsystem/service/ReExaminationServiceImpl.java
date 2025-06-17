package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.AppointmentRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ReExaminationServiceImpl implements ReExaminationService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void handleReExamination(TestResults testResult) {
        if (testResult.isRe_examination()) {
            Appointments originalAppointment = appointmentRepository
                    .findById(testResult.getAppointments().getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Original appointment not found"));

            Customers customers = testResult.getCustomers();
            Doctors doctors = testResult.getDoctors();
            LocalDate currentDate = originalAppointment.getAppointmentTime().plusDays(7);
            Schedules schedule = originalAppointment.getSchedules();

            // Lấy ngày bác sĩ làm việc

            List<Schedules> schedules = scheduleRepository.findByDoctors_DoctorId((doctors.getDoctorId()));
            if (schedules == null || schedules.isEmpty()) {
                throw new RuntimeException("No schedules found for doctor");
            }

            Schedules nextSchedule = schedules.stream()
                    .filter(s -> s.getWorkDate().isAfter(currentDate))
                    .min(Comparator.comparing(Schedules::getWorkDate))
                    .orElseThrow(() -> new RuntimeException("No upcoming schedule found for doctor after original appointment date"));
            // Giới hạn 30 ngày tìm kiếm
            LocalDate finalDate = null;
            for (int i = 0; i < 30; i++) {
                LocalDate checkDate = currentDate.plusDays(i);
                // Nếu đúng ngày bác sĩ làm việc
                if (checkDate.equals(nextSchedule.getWorkDate())) {
                    finalDate = checkDate;
                    break;
                }
            }


            if (finalDate == null) {
                throw new RuntimeException("No available re-exam date in next 30 days");
            }

            // Tạo lịch tái khám mới
            Appointments appointment = new Appointments();
            appointment.setCustomers(customers);
            appointment.setDoctors(doctors);
            appointment.setAppointmentType(testResult.getTestType());
            appointment.setAppointmentTime(finalDate); // ngày tái khám
            appointment.setAnonymous(originalAppointment.isAnonymous());
            appointment.setMedicalRecords(originalAppointment.getMedicalRecords());
            appointment.setSchedules(schedule);
            appointment.setStatus(true); // pending hoặc true nếu là boolean
            appointment.setStaffs(originalAppointment.getStaffs());

            appointmentRepository.save(appointment);
        }
    }


}
