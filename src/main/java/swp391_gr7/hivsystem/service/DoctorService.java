package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndDoctorCreateRequest;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.User;

public interface DoctorService {
    Doctor saveDoctor(UserAndDoctorCreateRequest request, User user);
}
