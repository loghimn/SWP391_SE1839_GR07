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
            LocalDate currentDate = originalAppointment.getAppointmentTime().plusDays(7);

            // lấy list appointment
            List<Appointments> listAppointments = appointmentRepository.findAll();
            if(listAppointments.isEmpty()){
                throw new AppException(ErrorCode.APPOINTMENT_LIST_NOT_FOUND);
            }
            while (true) {
                boolean duplicate_reExam = false;
                for(Appointments appointment: listAppointments){
                    if(appointment.getAppointmentTime().isEqual(currentDate) && appointment.isStatus()){
                        duplicate_reExam = true;
                        break;
                    }
                }
                if(duplicate_reExam){
                    currentDate = currentDate.plusDays(1);
                } else {
                    break;
                }
            }

            Schedules schedule = originalAppointment.getSchedules();


            // Lấy ngày bác sĩ làm việc
            List<Schedules> schedules = schedulesRepository.findByDoctors_DoctorId((doctors.getDoctorId()));
            if (schedules == null || schedules.isEmpty()) {
                throw new AppException(ErrorCode.RE_EXAMINATION_SCHEDULE_NOT_FOUND_FOR_DOCTOR);
            }

            LocalDate upperBound = currentDate.plusDays(30);

            LocalDate finalCurrentDate = currentDate;
            Schedules nextSchedule = schedules.stream()
                    .filter(s -> {
                        LocalDate workDate = s.getWorkDate();
                        return !workDate.isBefore(finalCurrentDate) && !workDate.isAfter(upperBound);
                    })
                    .min(Comparator.comparing(Schedules::getWorkDate))
                    .orElseThrow(() -> new AppException(ErrorCode.RE_EXAMINATION_NO_SCHEDULE_DOCTOR_FOUND_AFTER_ORIGINAL_APPOINTMENT_DATE));

            // Tạo lịch tái khám mới
            Appointments appointment = new Appointments();
            appointment.setCustomers(customers);
            appointment.setDoctors(doctors);
            appointment.setAppointmentType(testResult.getTestType());
            appointment.setAppointmentTime(nextSchedule.getWorkDate()); // ngày tái khám
            appointment.setAnonymous(originalAppointment.isAnonymous());
            appointment.setMedicalRecords(originalAppointment.getMedicalRecords());
            appointment.setSchedules(nextSchedule);
            appointment.setStatus(true); // pending hoặc true nếu là boolean
            appointment.setStaffs(originalAppointment.getStaffs());

            appointmentRepository.save(appointment);
        }
    }


}
