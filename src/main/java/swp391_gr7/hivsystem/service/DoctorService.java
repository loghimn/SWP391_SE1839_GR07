package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndDoctorCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.User;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(UserAndDoctorCreateRequest request, User user);
    List<Doctor> showAllDoctors();

    List<Doctor> showAllDoctorsActive();

    Doctor updateDoctor(UserAndDoctorUpdateRequest request, User user);
}
