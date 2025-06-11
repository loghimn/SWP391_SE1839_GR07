package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.util.List;

@Service
public class DoctorServiceImp implements DoctorService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(UserAndDoctorCreateRequest request, User user) {
        Doctor doctoc = new Doctor();
        doctoc.setUser(user);
        doctoc.setDepartment(request.getDepartment());
        doctoc.setYearExperience(request.getYearExperience());
        doctoc.setLicenseNumber(request.getLicenseNumber());
        return doctorRepository.save(doctoc);
    }

    @Override
    public List<Doctor> showAllDoctors() {
        return doctorRepository.findAllDoctors();
    }

    @Override
    public List<Doctor> showAllDoctorsActive() {
        return doctorRepository.findAllDoctorActive();
    }

    @Override
    public Doctor updateDoctor(UserAndDoctorUpdateRequest request, User user) {
        Doctor doctoc = doctorRepository.findDoctorByUser_UserId(user.getUserId().toString()).get();
        doctoc.setUser(user);
        doctoc.setDepartment(request.getDepartment());
        doctoc.setYearExperience(request.getYearExperience());
        doctoc.setLicenseNumber(request.getLicenseNumber());
        return doctorRepository.save(doctoc);
    }
}
