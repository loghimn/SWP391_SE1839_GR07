package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndDoctorUpdateRequest;
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

    @Override
    public Doctors saveDoctor(UserAndDoctorCreateRequest request, Users users) {
        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            return null; // Manager not found
        }
        Doctors doctor = new Doctors();
        doctor.setUsers(users);
        doctor.setDepartment(request.getDepartment());
        doctor.setYearExperience(request.getYearExperience());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setManagers(manager); // Associate manager
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctors> showAllDoctors() {
        return doctorRepository.findAllDoctors();
    }

    @Override
    public List<Doctors> showAllDoctorsActive() {
        return doctorRepository.findAllDoctorActive();
    }

    @Override
    public Doctors updateDoctor(UserAndDoctorUpdateRequest request, Users users) {
        Doctors doctoc = doctorRepository.findDoctorByUser_UserId(users.getUserId().toString()).get();
        doctoc.setUsers(users);
        doctoc.setDepartment(request.getDepartment());
        doctoc.setYearExperience(request.getYearExperience());
        doctoc.setLicenseNumber(request.getLicenseNumber());
        return doctorRepository.save(doctoc);
    }
}
