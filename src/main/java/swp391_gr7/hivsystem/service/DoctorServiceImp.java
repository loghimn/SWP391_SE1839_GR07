package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UpdatePasswordRequest;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.util.List;

@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Doctors saveDoctor(UserAndDoctorCreateRequest request, Users users) {
        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            return null; // Manager not found
        }
        if (doctorRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new AppException(ErrorCode.DOCTOR_INVALID_LICENSE_NUMBER_EXIST);
        }
        Doctors doctor = new Doctors();
        doctor.setUsers(users);
//        doctor.setDepartment(request.getDepartment());
        doctor.setYearExperience(request.getYearExperience());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setManagers(manager); // Associate manager
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctors> showAllDoctors() {
        if (doctorRepository.count() == 0) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        return doctorRepository.findAllDoctors();
    }

    @Override
    public List<Doctors> showAllDoctorsActive() {
        if (doctorRepository.count() == 0) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        return doctorRepository.findAllDoctorActive();
    }

    @Override
    public Doctors updateDoctor(UserAndDoctorUpdateRequest request, Users users) {
        Doctors doctor = doctorRepository.findDoctorByUser_UserId(users.getUserId().toString()).get();
        if (doctor.getLicenseNumber().equals(request.getLicenseNumber())) {
            doctor.setLicenseNumber(request.getLicenseNumber());
        } else if (doctorRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new AppException(ErrorCode.DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_EXIST);
        } else {
            doctor.setLicenseNumber(request.getLicenseNumber());
        }
        doctor.setUsers(users);
//        doctor.setDepartment(request.getDepartment());
        doctor.setYearExperience(request.getYearExperience());
        doctor.setLicenseNumber(request.getLicenseNumber());
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctors findDoctorById(int id) {
        if (!doctorRepository.existsById(id)) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        return doctorRepository.findDoctorByDoctorId(id);
    }

    @Override
    public boolean updatePasswordDoctor(int doctorId, UpdatePasswordRequest request) {
        Doctors doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        Users user = doctor.getUsers();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.USER_INVALID_OLD_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return true;
    }
}