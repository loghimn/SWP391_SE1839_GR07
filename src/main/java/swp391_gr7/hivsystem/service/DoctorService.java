package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndDoctorCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Users;

import java.util.List;

public interface DoctorService {
    Doctors saveDoctor(UserAndDoctorCreateRequest request, Users users);
    List<Doctors> showAllDoctors();

    List<Doctors> showAllDoctorsActive();

    Doctors updateDoctor(UserAndDoctorUpdateRequest request, Users users);

    Doctors findDoctorById(int id);
}
